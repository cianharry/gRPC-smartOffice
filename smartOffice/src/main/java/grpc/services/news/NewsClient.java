package grpc.services.news;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JOptionPane;

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
		streamNews();
	}
	
	// [1] UNARY RPC
	public static void switchNewsPower() {
		
		// Create Request message for use within the main method
		NewsPowerRequest newspowerequest = NewsPowerRequest.newBuilder().setNpower(true).build();
		
		NewsPowerResponse newspowerresponse = blockingStub.switchNewsPower(newspowerequest);
		
		if(newspowerresponse.getNpower()) {
			System.out.println("\nNews Stream activated...");
		}
		else {
			System.out.println("\nNews stream de-activated...");
		}
	}
	
	// [4] BIDIRECTIONAL RPC
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
               for(String content : newsContents) {
               	System.out.println("> " + content);
                   JOptionPane.showMessageDialog(null, content);
               }
               System.out.println("\nNews Headline Streaming completed");
           }
       };
       
       StreamObserver<NewsStreamRequest> requestObserver = asyncStub.streamNews(responseObserver);
       
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
	
}
