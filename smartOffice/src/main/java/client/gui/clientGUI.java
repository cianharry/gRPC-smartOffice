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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import grpc.services.news.NewsPowerRequest;
import grpc.services.news.NewsPowerResponse;
import grpc.services.news.NewsServiceGrpc;
import grpc.services.news.NewsStreamRequest;
import grpc.services.news.NewsStreamResponse;
import grpc.services.user.LoginRequest;
import grpc.services.user.LoginResponse;
import grpc.services.user.LogoutRequest;
import grpc.services.user.LogoutResponse;
import grpc.services.user.UserServiceGrpc;
import grpc.services.utilities.HeatPowerRequest;
import grpc.services.utilities.HeatPowerResponse;
import grpc.services.utilities.HeatTempRequest;
import grpc.services.utilities.HeatTempResponse;
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
	private static JTextField loginResponseTA, lightingResponseTA, lightsTF, lightsResponseTF;
	private static JTextArea newsResponseTA, heatResponseTA;
	private static JTextField HeatTF;
	private static JSlider lightSlider;
	
	private static int userPort, utilitiesPort, newsPort = 0;
	private static String host = "localhost";
	
	private static UserServiceGrpc.UserServiceBlockingStub blockingStub;
	private static UserServiceGrpc.UserServiceStub asyncStub;
	private static UtilitiesServiceGrpc.UtilitiesServiceBlockingStub utilBlockingStub;
	private static UtilitiesServiceGrpc.UtilitiesServiceStub utilAsyncStub;
	private static NewsServiceGrpc.NewsServiceBlockingStub newsBlockingStub;
	private static NewsServiceGrpc.NewsServiceStub newsAsyncStub;
	private static final int MIN = 0;
	private static final int MAX = 10;
	private static final int DEFAULT = 4;
	
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

		loginResponseTA = new JTextField("",20);
		loginResponseTA .setEditable(false);
		panel.add(loginResponseTA);

		panel.setLayout(boxlayout);

		return panel;

	}

	private JPanel getLightsPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JButton LightsOnBtn = new JButton("Lights On");
		LightsOnBtn.addActionListener(this);
		panel.add(LightsOnBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton LightsOffBtn = new JButton("Lights Off");
		LightsOffBtn.addActionListener(this);
		panel.add(LightsOffBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel LightsLB = new JLabel("Lights setting: ");
		panel.add(LightsLB);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		//initial light slider settings
		lightSlider = new JSlider(JSlider.HORIZONTAL,MIN, MAX, DEFAULT);
		//Turn on labels at major tick marks.
		lightSlider.setMajorTickSpacing(10);
		lightSlider.setMinorTickSpacing(1);
		lightSlider.setPaintTicks(true);
		lightSlider.setPaintLabels(true);
		
		panel.add(lightSlider);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton LightsSettingBtn = new JButton("Confirm");
		LightsSettingBtn.addActionListener(this);
		panel.add(LightsSettingBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		lightingResponseTA = new JTextField("",20);
		lightingResponseTA .setEditable(false);
		panel.add(lightingResponseTA);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));


		panel.setLayout(boxlayout);

		return panel;

	}
	
	private JPanel getHeatPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		

		JButton LightsOnBtn = new JButton("Aircon On");
		LightsOnBtn.addActionListener(this);
		panel.add(LightsOnBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton LightsOffBtn = new JButton("Aircon Off");
		LightsOffBtn.addActionListener(this);
		panel.add(LightsOffBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel HeatLB = new JLabel("Enter desired temp");
		panel.add(HeatLB);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		HeatTF = new JTextField("",10);
		panel.add(HeatTF);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton SelectHeatBtn = new JButton("Select");
		SelectHeatBtn.addActionListener(this);
		panel.add(SelectHeatBtn);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		heatResponseTA = new JTextArea(5, 15);
		heatResponseTA .setEditable(false);
		panel.add(heatResponseTA);

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

		newsResponseTA = new JTextArea(5, 15);
		newsResponseTA .setEditable(false);
		panel.add(newsResponseTA);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

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

		JFrame frame = new JFrame("Smart Office Dashboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(100, 200, 100, 200)));
	
		panel.add(getLoginPanel());
		panel.add(getLightsPanel());
		panel.add(getHeatPanel());
		panel.add(getNewsPanel());

		// Set size for the frame
		frame.setSize(600, 600);

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
			lightingResponseTA.setText("Lighting system turned on");
		}
		else {
			System.out.println("Lighting system has been turned off...");
			lightingResponseTA.setText("Lighting system turned off");
		}
	}
	
	public static void LightsOff() {
			
		// Create Request message for use within the main method
		LightPowerRequest lightpowerequest = LightPowerRequest.newBuilder().setLpower(false).build();
		
		LightPowerResponse lightpowerresponse = utilBlockingStub.switchLightPower(lightpowerequest);
		
		if(lightpowerresponse.getLpower()) {
			System.out.println("Lighting system has been turned on...");
			lightingResponseTA.setText("Lighting system turned on");
		}
		else {
			System.out.println("Lighting system has been turned off...");
			lightingResponseTA.setText("Lighting system turned off");
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
				System.out.println("Lights set");
				lightingResponseTA.setText("Choose again");
				
			}
		};
		
		
		StreamObserver<LightSettingRequest> requestObserver = utilAsyncStub.adjustLightSetting(responseObserver);
		
		try {
			// simulation of request stream from the client
			int lightSetting = lightSlider.getValue();
			requestObserver.onNext(LightSettingRequest.newBuilder().setSetting(lightSetting).build());
			
			Thread.sleep(new Random().nextInt(1000) + 500);
			
		} catch (RuntimeException e) {
			requestObserver.onError(e);
			throw e;
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		requestObserver.onCompleted();		
		}
		
		
		
		/*
		 * try {
			// simulation of request stream from the client
			requestObserver.onNext(LightSettingRequest.newBuilder().setSetting(lightSetting).build());
			
			Thread.sleep(new Random().nextInt(1000) + 500);
			
		} catch (RuntimeException e) {
			requestObserver.onError(e);
			throw e;
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		 */
		
	
	
	// 2. HEATING
	
	public static void HeatOn() {
		
		// Create Request message for use within the main method
		HeatPowerRequest heatpowerequest = HeatPowerRequest.newBuilder().setHpower(true).build();
		
		HeatPowerResponse heatpowerresponse = utilBlockingStub.switchHeatPower(heatpowerequest);
		
		if(heatpowerresponse.getHpower()) {
			System.out.println("Aircon system has been turned on...");
			heatResponseTA.setText("Heating system turned on");
		}
		else {
			System.out.println("Aircon system has been turned off...");
			heatResponseTA.setText("Heating system turned off");
		}
	}
	
	public static void HeatOff() {
		
		// Create Request message for use within the main method
		HeatPowerRequest heatpowerequest = HeatPowerRequest.newBuilder().setHpower(false).build();
		
		HeatPowerResponse heatpowerresponse = utilBlockingStub.switchHeatPower(heatpowerequest);
		
		if(heatpowerresponse.getHpower()) {
			System.out.println("Aircon system has been turned on...");
			heatResponseTA.setText("Heating system turned on");
		}
		else {
			System.out.println("Aircon system has been turned off...");
			heatResponseTA.setText("Heating system turned off");
		}
	}
	
	// [3] Server-streaming RPC
	public static void selectHeatTemp() {
		
		int heat = Integer.parseInt(HeatTF.getText().toString());
		HeatTempRequest request = HeatTempRequest.newBuilder().setTemp(heat).build();
		
		System.out.println("Requesting to set office aircon to "+heat+"°C");
		
		StreamObserver<HeatTempResponse> responseObserverHeat = new StreamObserver<HeatTempResponse>() {
			
			@Override
			public void onNext(HeatTempResponse htr) {
				heatResponseTA.append("Responding: "+htr.getTemp()+"°C");
				System.out.println("Responding: "+htr.getTemp()+"°C");
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Office temperature has reached the selected level: "+heat+"°C");
				heatResponseTA.setText("Office temperature has reached the selected level: "+heat+"°C");
			}
		};
		
		utilAsyncStub.selectHeatTemp(request, responseObserverHeat);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		
		if(label.equals("Lights On") || label.equals("Lights Off") || label.equals("Aircon On") || label.equals("Aircon Off") || label.equals("Select") || label.equals("Confirm")){
			System.out.println("\nUtilities microservice being invoked ...");
			
			try {
				ManagedChannel utilitiesChannel = ManagedChannelBuilder.forAddress(host, utilitiesPort).usePlaintext().build();
				
				utilBlockingStub = UtilitiesServiceGrpc.newBlockingStub(utilitiesChannel);
				utilAsyncStub = UtilitiesServiceGrpc.newStub(utilitiesChannel);
				if (label.equals("Lights On")){
					LightsOn();
				}
				else if (label.equals("Lights Off")){
					LightsOff();
				}
				else if (label.equals("Aircon On")){
					HeatOn();
				}
				else if(label.equals("Aircon Off")) {
					HeatOff();
				}
				else if(label.equals("Confirm")) {
					adjustLightSetting();
				}
				else {
					selectHeatTemp();
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
