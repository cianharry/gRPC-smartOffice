package grpc.services.utilities;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import grpc.services.utilities.UtilitiesServiceGrpc.UtilitiesServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class UtilitiesServer extends UtilitiesServiceImplBase {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Starting gRPC Utilities Server");
		
		// Create & Register utilities service with jmDNS
		try {
			int PORT = 50051;
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	        ServiceInfo serviceInfo = ServiceInfo.create("_utilities._tcp.local.", "utilities", PORT, "Utilities server gives you access to lighting and air conditioning");
	        jmdns.registerService(serviceInfo);
	        UtilitiesServer utServer = new UtilitiesServer();
	        Server server = ServerBuilder.forPort(PORT)
                    .addService(utServer)
                    .build()
                    .start();
            System.out.println("Utilities server started, listening on " + PORT);
            server.awaitTermination();
            
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
		
	}
	
	/*
	 * ------------------------------------------- LIGHTS ------------------------------------------------------
	 */
	
	//-------------------------------- Unary RPC implementation -------------------------------------------------
	
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
	
	//-------------------------------- Client Stream RPC implementation -------------------------------------------------
	
	@Override
	public StreamObserver<LightSettingRequest> adjustLightSetting(final StreamObserver<LightSettingResponse> responseObserver) {
		return new StreamObserver<LightSettingRequest>() {
			//initialising variable to handle input
			int setting = 0;
			
			public void onNext(LightSettingRequest lsr) {
				// set the variable equal to the value of the incoming request
				setting = lsr.getSetting();
				System.out.println("Request recieved to adjust lights to: " + setting);
			}
			
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			// build the response using the setting variable
			public void onCompleted() {
				LightSettingResponse response = LightSettingResponse.newBuilder().setSetting(setting).build();
				responseObserver.onNext(response);
				responseObserver.onCompleted();
			}
		};
	}
	
	
	/*
	 * ------------------------------------------- HEAT ------------------------------------------------------
	 */
	
	//-------------------------------- Unary RPC implementation -------------------------------------------------
	
	@Override
	public void switchHeatPower(HeatPowerRequest request, StreamObserver<HeatPowerResponse> responseObserver) {
		// notification that the method has been invoked
		System.out.println("Receiving a request for aircon system power!");
		
		// get the hpower boolean defined in the HeatPowerRequest.java file
		boolean hpower = request.getHpower();
		
		// if the value is true the aircon system is on, otherwise its off
		if(hpower) {
			System.out.println("Turning aircon system on...");
		}
		else {
			System.out.println("Turning aircon system off...");
		}
		
		HeatPowerResponse response = HeatPowerResponse.newBuilder().setHpower(hpower).build();
		
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
	
	//-------------------------------- Server Stream RPC implementation -------------------------------------------------
	
	@Override
	public void selectHeatTemp(HeatTempRequest request, StreamObserver<HeatTempResponse> responseObserverHeat) {
		int temp = request.getTemp();
		
		System.out.println("Request recieved to set office aircon to "+temp+"°C");
		try {
			HeatTempResponse response = HeatTempResponse.newBuilder().setTemp(temp-4).build();
			HeatTempResponse response1 = HeatTempResponse.newBuilder().setTemp(temp-3).build();
			HeatTempResponse response2 = HeatTempResponse.newBuilder().setTemp(temp-2).build();
			HeatTempResponse response3 = HeatTempResponse.newBuilder().setTemp(temp-1).build();
			HeatTempResponse response4 = HeatTempResponse.newBuilder().setTemp(temp).build();
			
			responseObserverHeat.onNext(response);
			 try {
		            Thread.sleep(2000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			responseObserverHeat.onNext(response1);
			 try {
		            Thread.sleep(2000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			responseObserverHeat.onNext(response2);
			 try {
		            Thread.sleep(2000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			responseObserverHeat.onNext(response3);
			 try {
		            Thread.sleep(2000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			responseObserverHeat.onNext(response4);
		} catch (Error e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

		responseObserverHeat.onCompleted();
	}
	
}
