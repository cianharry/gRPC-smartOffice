package grpc.services.utilities;

import java.io.IOException;

import grpc.services.utilities.UtilitiesServiceGrpc.UtilitiesServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class UtilitiesServer extends UtilitiesServiceImplBase {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Starting gRPC Utilities Server");
		UtilitiesServer utilitiesserver = new UtilitiesServer();
		
		int port = 50051;
		
		try {
			Server server = ServerBuilder.forPort(port)
					.addService(utilitiesserver)
					.build()
					.start();

			System.out.println("Server started with Port:" + server.getPort());
		    server.awaitTermination();

		}//try
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
	
}
