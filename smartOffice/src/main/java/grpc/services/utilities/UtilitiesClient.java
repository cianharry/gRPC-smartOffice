package grpc.services.utilities;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class UtilitiesClient {
	
	private static UtilitiesServiceGrpc.UtilitiesServiceBlockingStub blockingStub;
	private static UtilitiesServiceGrpc.UtilitiesServiceStub asyncStub;
	
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
            //String address = "localhost";
            
            
        }
    }
	
	public static void main(String[] args) throws Exception {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		
		blockingStub = UtilitiesServiceGrpc.newBlockingStub(channel);
		asyncStub = UtilitiesServiceGrpc.newStub(channel);
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			// Add a service listener
			jmdns.addServiceListener("_utilities._tcp.local.", new Listener());

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		switchLightPower();
		adjustLightSetting();
		switchHeatPower();
		selectHeatTemp();
	}
	
	
	
	// [1] UNARY RPC
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
	
	// [2] CLIENT STREAMING RPC
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
				System.out.println("Lights set [slider value]");
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
	
	// [1] UNARY RPC
	public static void switchHeatPower() {
		
		// Create Request message for use within the main method
		HeatPowerRequest heatpowerequest = HeatPowerRequest.newBuilder().setHpower(true).build();
		
		HeatPowerResponse heatpowerresponse = blockingStub.switchHeatPower(heatpowerequest);
		
		if(heatpowerresponse.getHpower()) {
			System.out.println("Aircon system has been turned on...");
		}
		else {
			System.out.println("Aircon system has been turned off...");
		}
	}
	
	// [3] Server-streaming RPC
	public static void selectHeatTemp() {
		
		HeatTempRequest request = HeatTempRequest.newBuilder().setTemp(18).build();
		
		System.out.println("Requesting to set office aircon to "+request.getTemp()+"°C");
		
		StreamObserver<HeatTempResponse> responseObserverHeat = new StreamObserver<HeatTempResponse>() {
			
			@Override
			public void onNext(HeatTempResponse htr) {
				System.out.println("Temperature currently: " + htr.getTemp() + "°C");

			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Office temperature has reached the selected level: "+request.getTemp()+"°C");
			}
		};
		
		asyncStub.selectHeatTemp(request, responseObserverHeat);
		
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
