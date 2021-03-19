package grpc.services.user;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import grpc.services.utilities.UtilitiesClient.Listener;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UserClient {

	private static UserServiceGrpc.UserServiceBlockingStub blockingStub;
	
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
            ServiceInfo info = serviceEvent.getInfo();
            int Port = serviceEvent.getInfo().getPort();
            String address = info.getHostAddresses()[0];
            //String address = "localhost";
            
            
        }
    }
	

	public static void main(String[] args) {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		blockingStub = UserServiceGrpc.newBlockingStub(channel);
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			// Add a service listener
			jmdns.addServiceListener("_user._tcp.local.", new Listener());

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}


		login();
		logout();
	}

	//Login
	public static void login() {
		System.out.println("Inside Login Client: ");

		LoginRequest loginRequest = LoginRequest.newBuilder().setUsername("Cian").setPassword("Dublin").build();

		LoginResponse response = blockingStub.login(loginRequest);

		System.out.println(response);
	}

	//Logout
	public static void logout() {
		System.out.println("Inside Logout Client: ");

		LogoutRequest logoutRequest = LogoutRequest.newBuilder().setUsername("Cian").build();

		LogoutResponse response = blockingStub.logout(logoutRequest);

		System.out.println(response);
	}
}
