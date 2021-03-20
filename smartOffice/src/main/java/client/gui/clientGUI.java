package client.gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import grpc.services.user.LoginRequest;
import grpc.services.user.LoginResponse;
import grpc.services.user.LogoutRequest;
import grpc.services.user.LogoutResponse;
import grpc.services.user.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class clientGUI implements ActionListener {
	
	private static JTextField usernameTF, passwordTF;
	private static JTextArea loginResponseTA;
	private JTextField entry2, reply2;
	private JTextField entry3, reply3;
	private JTextField entry4, reply4;
	
	private static int userPort, utilitiesPort, newsPort = 0;
	private static String host = "localhost";
	
	private static UserServiceGrpc.UserServiceBlockingStub blockingStub;
	private static UserServiceGrpc.UserServiceStub asyncStub;
	
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
	
	

	private JPanel getService1JPanel() {

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

		JButton button = new JButton("Login");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		loginResponseTA = new JTextArea(0,10);
		loginResponseTA .setEditable(false);
		panel.add(loginResponseTA);

		panel.setLayout(boxlayout);

		return panel;

	}

	private JPanel getService2JPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel label = new JLabel("Enter value")	;
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry2 = new JTextField("",10);
		panel.add(entry2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button = new JButton("Utilities");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		reply2 = new JTextField("", 10);
		reply2 .setEditable(false);
		panel.add(reply2 );

		panel.setLayout(boxlayout);

		return panel;

	}

	private JPanel getService3JPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel label = new JLabel("Enter value")	;
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry3 = new JTextField("",10);
		panel.add(entry3);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button = new JButton("News");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		reply3 = new JTextField("", 10);
		reply3 .setEditable(false);
		panel.add(reply3 );

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
	
		panel.add(getService1JPanel());
		panel.add(getService2JPanel());
		panel.add(getService3JPanel());

		// Set size for the frame
		frame.setSize(300, 300);

		// Set the window to be visible as the default to be false
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	//Login
		public static void login() {
			System.out.println("Inside Login Client: ");

			LoginRequest loginRequest = LoginRequest.newBuilder().setUsername(usernameTF.getText()).setPassword(passwordTF.getText()).build();

			LoginResponse response = blockingStub.login(loginRequest);
			
			loginResponseTA.setText(response.toString());

			System.out.println(response);
		}

		//Logout
		public static void logout() {
			System.out.println("Inside Logout Client: ");

			LogoutRequest logoutRequest = LogoutRequest.newBuilder().setUsername(usernameTF.getText()).build();

			LogoutResponse response = blockingStub.logout(logoutRequest);

			System.out.println(response);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		String label = button.getActionCommand();
		
		if (label.equals("Login")) {
			System.out.println("User authentication service to be invoked ...");
			
			try {
				ManagedChannel channel = ManagedChannelBuilder.forAddress(host, 50050).usePlaintext().build();
				blockingStub = UserServiceGrpc.newBlockingStub(channel);
				asyncStub = UserServiceGrpc.newStub(channel);
				login();
			} catch (IllegalArgumentException i) {
	            System.out.println("Incorrect PORT for User server");
	        }
			
			
		}
	}

}
