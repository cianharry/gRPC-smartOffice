package grpc.services.news;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import grpc.services.utilities.LightPowerRequest;
import grpc.services.utilities.LightPowerResponse;
import grpc.services.utilities.UtilitiesServiceGrpc;
import grpc.services.utilities.UtilitiesClient.Listener;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class NewsClient {
	
	private static NewsServiceGrpc.NewsServiceBlockingStub blockingStub;
	private static NewsServiceGrpc.NewsServiceStub asyncStub;
	
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
            final int Port = serviceEvent.getInfo().getPort();
            String address = info.getHostAddresses()[0];
        }
	}
	
	public static void main(String[] args) throws Exception {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();
		
		blockingStub = NewsServiceGrpc.newBlockingStub(channel);
		asyncStub = NewsServiceGrpc.newStub(channel);
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			// Add a service listener
			jmdns.addServiceListener("_news._tcp.local.", new Listener());

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		switchNewsPower();
	}
	
	// [1] UNARY RPC
	public static void switchNewsPower() {
		
		// Create Request message for use within the main method
		NewsPowerRequest newspowerequest = NewsPowerRequest.newBuilder().setNpower(true).build();
		
		NewsPowerResponse newspowerresponse = blockingStub.switchNewsPower(newspowerequest);
		
		if(newspowerresponse.getNpower()) {
			System.out.println("News Stream activated...");
		}
		else {
			System.out.println("News stream de-activated...");
		}
	}
	

}
