package grpc.services.utilities;

import java.util.Random;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class UtilitiesClient {
	
	private static UtilitiesServiceGrpc.UtilitiesServiceBlockingStub blockingStub;
	private static UtilitiesServiceGrpc.UtilitiesServiceStub asyncStub;
	
	public static void main(String[] args) throws Exception {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		
		blockingStub = UtilitiesServiceGrpc.newBlockingStub(channel);
		asyncStub = UtilitiesServiceGrpc.newStub(channel);
		
		switchHeatPower();
		switchLightPower();
		adjustLightSetting();
	}
	
	// UNARY RPC
	public static void switchLightPower() {
		
		// Create Request message for use within the main method
		LightPowerRequest lightpowerequest = LightPowerRequest.newBuilder().setLpower(true).build();
		
		LightPowerResponse lightpowerresponse = blockingStub.switchLightPower(lightpowerequest);
		
		if(lightpowerresponse.getLpower()) {
			System.out.println("Lighting system has been turned on...");
		}
		else {
			System.out.println("Lighting system has been turned off...");
		}
	}
	
	// CLIENT STREAMING RPC
	public static void adjustLightSetting() {
		
		StreamObserver<LightSettingResponse> responseObserver = new StreamObserver<LightSettingResponse>() {
			
			@Override
			public void onNext(LightSettingResponse lsr) {
				System.out.println("Ligthing has been adjusted to level " + lsr.getSetting());
			}
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			@Override
			public void onCompleted() {

			}
		};
		
		StreamObserver<LightSettingRequest> requestObserver = asyncStub.adjustLightSetting(responseObserver);
		
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
	
	// UNARY RPC
	public static void switchHeatPower() {
		
		// Create Request message for use within the main method
		HeatPowerRequest heatpowerequest = HeatPowerRequest.newBuilder().setHpower(false).build();
		
		HeatPowerResponse heatpowerresponse = blockingStub.switchHeatPower(heatpowerequest);
		
		if(heatpowerresponse.getHpower()) {
			System.out.println("Aircon system has been turned on...");
		}
		else {
			System.out.println("Aircon system has been turned off...");
		}
	}
}
