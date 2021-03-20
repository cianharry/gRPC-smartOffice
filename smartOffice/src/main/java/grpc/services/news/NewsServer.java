package grpc.services.news;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import grpc.services.news.NewsServiceGrpc.NewsServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class NewsServer extends NewsServiceImplBase {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		try {
			int PORT = 50052;
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			
			// Register news service
	        ServiceInfo serviceInfo = ServiceInfo.create("_news._tcp.local.", "news", PORT, "News server gives you access to the latest financial news headlines");
	        jmdns.registerService(serviceInfo);
	        NewsServer newsServer = new NewsServer();
	        
	        Server server = ServerBuilder.forPort(PORT)
                    .addService(newsServer)
                    .build()
                    .start();
            System.out.println("News server started, listening on " + PORT);
            server.awaitTermination();
			
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
	}
	
	@Override
	public void switchNewsPower(NewsPowerRequest request, StreamObserver<NewsPowerResponse> responseObserver) {
		// notification that the method has been invoked
		System.out.println("Receiving a request for news streaming service!");
		
		// get the npower boolean defined in the NewsPowerRequest.java file
		boolean npower = request.getNpower();
		
		// if the value is true the news stream is active, otherwise its inactive
		if(npower) {
			System.out.println("News streaming active...");
		}
		else {
			System.out.println("News streaming inactive...");
		}
		
		NewsPowerResponse response = NewsPowerResponse.newBuilder().setNpower(npower).build();
		
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
	
	@Override
	public StreamObserver<NewsStreamRequest> streamNews(StreamObserver<NewsStreamResponse> responseObserver) {
		return new StreamObserver<NewsStreamRequest>() {
			 @Override
	            public void onNext(NewsStreamRequest value) {
	                StringBuilder sb = new StringBuilder(value.getContent());
	                System.out.println("Building News Stream: " + value.getContent());
	                
	                NewsStreamResponse res = NewsStreamResponse.newBuilder().setContent(sb.toString()).build();
	                responseObserver.onNext(res);
	            }

	            @Override
	            public void onError(Throwable t) {
	                System.out.println("Error: " + t.getMessage());
	                t.printStackTrace();
	            }

	            @Override
	            public void onCompleted() {
	                responseObserver.onCompleted();
	            }
		};
	}
	
}
