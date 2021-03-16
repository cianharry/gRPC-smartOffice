package grpc.services.utilities;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UtilitiesClient {
	
	private static UtilitiesServiceGrpc.UtilitiesServiceBlockingStub blockingStub;
	
	public static void main(String[] args) throws Exception {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		
		blockingStub = UtilitiesServiceGrpc.newBlockingStub(channel);
		
		switchLightPower();
	}
	
	public static void switchLightPower() {
		
		// Create Request message for use within the main method
		LightPowerRequest lightpowerequest = LightPowerRequest.newBuilder().setLpower(true).build();
		
		// Create Response message for use within the main method
		LightPowerResponse lightpowerresponse = blockingStub.switchLightPower(lightpowerequest);
		
		if(lightpowerresponse.getLpower()) {
			System.out.println("Lighting system has been turned on...");
		}
		else {
			System.out.println("Lighting system has been turned off...");
		}
	}
}
