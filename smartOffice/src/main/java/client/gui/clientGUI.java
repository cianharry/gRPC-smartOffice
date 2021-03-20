package client.gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import grpc.services.news.NewsPowerRequest;
import grpc.services.news.NewsPowerResponse;
import grpc.services.news.NewsServiceGrpc;
import grpc.services.news.NewsStreamRequest;
import grpc.services.news.NewsStreamResponse;
import grpc.services.user.LoginRequest;
import grpc.services.user.LoginResponse;
import grpc.services.user.LogoutRequest;
import grpc.services.user.LogoutResponse;
import grpc.services.user.UserClient;
import grpc.services.user.UserServiceGrpc;
import grpc.services.utilities.HeatPowerRequest;
import grpc.services.utilities.HeatPowerResponse;
import grpc.services.utilities.LightPowerRequest;
import grpc.services.utilities.LightPowerResponse;
import grpc.services.utilities.LightSettingRequest;
import grpc.services.utilities.LightSettingResponse;
import grpc.services.utilities.UtilitiesServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class clientGUI implements ActionListener {
	
	private static JTextField usernameTF, passwordTF;
	private static JTextArea loginResponseTA, lightingResponseTA, newsResponseTA;
	private JTextField entry2, reply2;
	private JTextField entry3, reply3;
	private JTextField entry4, reply4;
	
	private static int userPort, utilitiesPort, newsPort = 0;
	private static String host = "localhost";
	
	private static UserServiceGrpc.UserServiceBlockingStub blockingStub;
	private static UserServiceGrpc.UserServiceStub asyncStub;
	private static UtilitiesServiceGrpc.UtilitiesServiceBlockingStub utilBlockingStub;
	private static UtilitiesServiceGrpc.UtilitiesServiceStub utilAsyncStub;
	private static NewsServiceGrpc.NewsServiceBlockingStub newsBlockingStub;
	private static NewsServiceGrpc.NewsServiceStub newsAsyncStub;
	
	public static class Listener implements ServiceListener {
        @Override
        public void serviceAdded(ServiceEvent serviceEvent) {
            System.out.println("Service added: " + serviceEvent.getInfo());
        }

        @Override
        public void serviceRemoved(ServiceEvent serviceEvent) {
            System.out.println("Service removed: " + serviceEvent.getInfo());
        }

        @Override
        public void serviceResolved(ServiceEvent serviceEvent) {
            System.out.println("Service resolved: " + serviceEvent.getInfo());
            if (serviceEvent.getName().equals("user")) {
                userPort = serviceEvent.getInfo().getPort();
            } else if (serviceEvent.getName().equals("utilities")) {
                utilitiesPort = serviceEvent.getInfo().getPort();
            } else if (serviceEvent.getName().equals("news")){
                newsPort = serviceEvent.getInfo().getPort();
            }
        }
    }
	
	

	private JPanel getLoginPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel usernameLB = new JLabel("Username: ")	;
		panel.add(usernameLB);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		usernameTF = new JTextField("",10);
		panel.add(usernameTF);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel passwordLb = new JLabel("Password: ")	;
		panel.add(passwordLb);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		passwordTF = new JTextField("",10);
		panel.add(passwordTF);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(this);
		panel.add(LoginBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton LogoutBtn = new JButton("Logout");
		LogoutBtn.addActionListener(this);
		panel.add(LogoutBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		loginResponseTA = new JTextArea(0,10);
		loginResponseTA .setEditable(false);
		panel.add(loginResponseTA);

		panel.setLayout(boxlayout);

		return panel;

	}

	private JPanel getUtilitiesPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel label = new JLabel("Enter value")	;
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry2 = new JTextField("",10);
		panel.add(entry2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton LightsOnBtn = new JButton("Lights On");
		LightsOnBtn.addActionListener(this);
		panel.add(LightsOnBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton LightsOffBtn = new JButton("Lights Off");
		LightsOffBtn.addActionListener(this);
		panel.add(LightsOffBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		lightingResponseTA = new JTextArea(0,10);
		lightingResponseTA .setEditable(false);
		panel.add(lightingResponseTA);

		panel.setLayout(boxlayout);

		return panel;

	}

	private JPanel getNewsPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JButton NewsPowerBtn = new JButton("Status");
		NewsPowerBtn.addActionListener(this);
		panel.add(NewsPowerBtn);
		panel.add(Box.createRigidArea(new Dimension(20, 0)));
		
		JButton NewsStreamBtn = new JButton("Headlines");
		NewsStreamBtn.addActionListener(this);
		panel.add(NewsStreamBtn);
		panel.add(Box.createRigidArea(new Dimension(20, 0)));

		newsResponseTA = new JTextArea(0, 20);
		newsResponseTA .setEditable(false);
		panel.add(newsResponseTA);

		panel.setLayout(boxlayout);

		return panel;

	}
	
	public static void main(String[] args) {

		clientGUI gui = new clientGUI();

		gui.build();
		
		try {
            // create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            // add service listeners for TV, lights, and curtain server
            jmdns.addServiceListener("_user._tcp.local.", new clientGUI.Listener());
            jmdns.addServiceListener("_utilities._tcp.local.", new clientGUI.Listener());
            jmdns.addServiceListener("_news._tcp.local.", new clientGUI.Listener());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	private void build() { 

		JFrame frame = new JFrame("Smart Office Home");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));
	
		panel.add(getLoginPanel());
		panel.add(getUtilitiesPanel());
		panel.add(getNewsPanel());

		// Set size for the frame
		frame.setSize(300, 500);

		// Set the window to be visible as the default to be false
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	/*
	 * --------------------------- USER AUTHENTICATION SERVICE --------------------------------------
	 */
	
	//Login
	public static void login() {
		System.out.println("logging in...");

		LoginRequest loginRequest = LoginRequest.newBuilder().setUsername(usernameTF.getText()).setPassword(passwordTF.getText()).build();

		LoginResponse response = blockingStub.login(loginRequest);
		
		loginResponseTA.setText(response.getResponseMessage().toString());

		System.out.println(response);
	}

	//Logout
	public static void logout() {
		System.out.println("logging in...");

		LogoutRequest logoutRequest = LogoutRequest.newBuilder().setUsername(usernameTF.getText()).build();

		LogoutResponse response = blockingStub.logout(logoutRequest);
		
		loginResponseTA.setText(response.getResponseMessage().toString());

		System.out.println(response);
	}
	
	/*
	 * --------------------------- UTILITIES SERVICE --------------------------------------
	 */
	
	// 1. LIGHTS
	public static void LightsOn() {
			
		// Create Request message for use within the main method
		LightPowerRequest lightpowerequest = LightPowerRequest.newBuilder().setLpower(true).build();
		
		LightPowerResponse lightpowerresponse = utilBlockingStub.switchLightPower(lightpowerequest);
		
		if(lightpowerresponse.getLpower()) {
			System.out.println("Lighting system has been turned on...");
		}
		else {
			System.out.println("Lighting system has been turned off...");
		}
	}
	
	public static void LightsOff() {
			
		// Create Request message for use within the main method
		LightPowerRequest lightpowerequest = LightPowerRequest.newBuilder().setLpower(false).build();
		
		LightPowerResponse lightpowerresponse = utilBlockingStub.switchLightPower(lightpowerequest);
		
		if(lightpowerresponse.getLpower()) {
			System.out.println("Lighting system has been turned on...");
		}
		else {
			System.out.println("Lighting system has been turned off...");
		}
	}
	
	public static void adjustLightSetting() {
		
		StreamObserver<LightSettingResponse> responseObserver = new StreamObserver<LightSettingResponse>() {
			
			@Override
			public void onNext(LightSettingResponse lsr) {
				
			}
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			@Override
			public void onCompleted() {
				System.out.println("Lights set [slider value]");
			}
		};
		
		StreamObserver<LightSettingRequest> requestObserver = utilAsyncStub.adjustLightSetting(responseObserver);
		
		try {
			// simulation of request stream from the client
			requestObserver.onNext(LightSettingRequest.newBuilder().setSetting(3).build());
			System.out.println("3");
			requestObserver.onNext(LightSettingRequest.newBuilder().setSetting(4).build());
			System.out.println("4");
			requestObserver.onNext(LightSettingRequest.newBuilder().setSetting(5).build());
			System.out.println("5");
			requestObserver.onNext(LightSettingRequest.newBuilder().setSetting(6).build());
			System.out.println("6");
			requestObserver.onNext(LightSettingRequest.newBuilder().setSetting(7).build());
			System.out.println("7");
			
			Thread.sleep(new Random().nextInt(1000) + 500);
			
		} catch (RuntimeException e) {
			requestObserver.onError(e);
			throw e;
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		requestObserver.onCompleted();
	}
	
	
	// 2. HEATING
	
	public static void HeatOn() {
		
		// Create Request message for use within the main method
		HeatPowerRequest heatpowerequest = HeatPowerRequest.newBuilder().setHpower(true).build();
		
		HeatPowerResponse heatpowerresponse = utilBlockingStub.switchHeatPower(heatpowerequest);
		
		if(heatpowerresponse.getHpower()) {
			System.out.println("Aircon system has been turned on...");
		}
		else {
			System.out.println("Aircon system has been turned off...");
		}
	}
	
	public static void HeatOff() {
		
		// Create Request message for use within the main method
		HeatPowerRequest heatpowerequest = HeatPowerRequest.newBuilder().setHpower(false).build();
		
		HeatPowerResponse heatpowerresponse = utilBlockingStub.switchHeatPower(heatpowerequest);
		
		if(heatpowerresponse.getHpower()) {
			System.out.println("Aircon system has been turned on...");
		}
		else {
			System.out.println("Aircon system has been turned off...");
		}
	}
	
	/*
	 * --------------------------- NEWS SERVICE --------------------------------------
	 */
	
	public static void newsStatus() {
		
		// Create Request message for use within the main method
		NewsPowerRequest newspowerequest = NewsPowerRequest.newBuilder().setNpower(true).build();
		
		NewsPowerResponse newspowerresponse = newsBlockingStub.switchNewsPower(newspowerequest);
		
		if(newspowerresponse.getNpower()) {
			System.out.println("\nNews Stream activated...");
		}
		else {
			System.out.println("\nNews stream de-activated...");
		}
	}
	
	
	public static void streamNews() {
		
		ArrayList<String> newsContents = new ArrayList<>();
		 
       StreamObserver<NewsStreamResponse> responseObserver = new StreamObserver<NewsStreamResponse>() {
           @Override
           public void onNext(NewsStreamResponse nsr) {
               System.out.println("> gathering... "+nsr.getContent());
               newsContents.add(nsr.getContent());
           }

           @Override
           public void onError(Throwable t) {
               System.out.println("Error: " + t.getMessage());
               t.printStackTrace();
           }

           @Override
           public void onCompleted() {
        	   System.out.println("\nNews Headlines: " + newsContents.size());
        	   newsResponseTA.append("\nNews Headlines: " + newsContents.size());
               for(String content : newsContents) {
               		System.out.println("> " + content);
               		newsResponseTA.append("\n"+content);
               		JOptionPane.showMessageDialog(null, content);
               }
               System.out.println("\nNews Headline Streaming completed");
           }
       };
       
       StreamObserver<NewsStreamRequest> requestObserver = newsAsyncStub.streamNews(responseObserver);
       
       try {
    	   requestObserver.onNext(NewsStreamRequest.newBuilder().setContent("Nasdaq index has fallen by 2.1% overnight").build());
    	   requestObserver.onNext(NewsStreamRequest.newBuilder().setContent("FTSE 100 index increased 0.7% overnight").build());
    	   requestObserver.onNext(NewsStreamRequest.newBuilder().setContent("Dow Jones Industrial Avergae at an all time low").build());
    	   
    	   Thread.sleep(new Random().nextInt(1000) + 500);
    	   
       } catch (RuntimeException e) {
			requestObserver.onError(e);
			throw e;
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
           
        requestObserver.onCompleted();
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		String label = button.getActionCommand();
		
		if (label.equals("Login") || label.equals("Logout")) {
			System.out.println("\nUser authentication microservice being invoked ...");
			
			try {
				ManagedChannel userChannel = ManagedChannelBuilder.forAddress(host, userPort).usePlaintext().build();
				
				blockingStub = UserServiceGrpc.newBlockingStub(userChannel);
				asyncStub = UserServiceGrpc.newStub(userChannel);
				
				if (label.equals("Login")){
					login();
				}
				else {
					logout();
				}
				userChannel.shutdown();
				
			} catch (IllegalArgumentException i) {
	            System.out.println("Incorrect PORT for User server");
	        }
		}
		
		if(label.equals("Lights On") || label.equals("Lights Off")) {
			System.out.println("\nUtilities microservice being invoked ...");
			
			try {
				ManagedChannel utilitiesChannel = ManagedChannelBuilder.forAddress(host, utilitiesPort).usePlaintext().build();
				
				utilBlockingStub = UtilitiesServiceGrpc.newBlockingStub(utilitiesChannel);
				utilAsyncStub = UtilitiesServiceGrpc.newStub(utilitiesChannel);
				if (label.equals("Lights On")){
					LightsOn();
				}
				else {
					LightsOff();
				}
				utilitiesChannel.shutdown();
				
			} catch (IllegalArgumentException i) {
	            System.out.println("Incorrect PORT for Utilities server");
	        }
			
		}
		
		if(label.equals("Status") || label.equals("Headlines")) {
			System.out.println("\nNews streaming microservice being invoked ...");
			
			try {
				ManagedChannel newsChannel = ManagedChannelBuilder.forAddress(host, newsPort).usePlaintext().build();
				
				newsBlockingStub = NewsServiceGrpc.newBlockingStub(newsChannel);
				newsAsyncStub = NewsServiceGrpc.newStub(newsChannel);
				if (label.equals("Status")){
					newsStatus();
				}
				else {
					streamNews();
				}
				newsChannel.shutdown();
				
				
			} catch (IllegalArgumentException i) {
	            System.out.println("Incorrect PORT for Utilities server");
	        }
		}
	}

}
