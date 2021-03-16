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
	
	@Override
	public void switchLightPower(LightPowerRequest request, StreamObserver<LightPowerResponse> responseObserver) {
		// notification that the method has been invoked
		System.out.println("Receiving a request for lighting system power!");
		
		// get the lpower boolean defined in the LightPowerRequest.java file
		boolean lpower = request.getLpower();
		
		// if the value is true the lights are on, otherwise the lights are off
		if(lpower) {
			System.out.println("Turning lights on...");
		}
		else {
			System.out.println("Turning lights off...");
		}
		
		LightPowerResponse response = LightPowerResponse.newBuilder().setLpower(lpower).build();
		
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
}
