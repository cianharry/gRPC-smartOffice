package grpc.services.user;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import grpc.services.user.UserServiceGrpc.UserServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class UserServer extends UserServiceImplBase{

	public static void main(String[] args) throws IOException, InterruptedException {
		// configuration of the user auth server
		System.out.println("Starting gRPC User Authentication Server");
	
		try {
			int PORT = 50050;
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			
			// Create & Register user authentication service using jmDNS
	        ServiceInfo serviceInfo = ServiceInfo.create("_user._tcp.local.", "user", PORT, "User server authenticates employees");
	        jmdns.registerService(serviceInfo);
	        UserServer userServer = new UserServer();
	        Server server = ServerBuilder.forPort(PORT)
	                .addService(userServer)
	                .build()
	                .start();
	        System.out.println("User authentication server started, listening on " + PORT);
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
	public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
		System.out.println("Authenticating user credentials");
		String username = request.getUsername();
		String password = request.getPassword();

		LoginResponse.Builder response = LoginResponse.newBuilder();
		System.out.println("username: " + username);
		System.out.println("password: " + password);

		if(username.equals("Cian") && password.equals("Dublin")) {
			// return Success response
			response.setResponseCode(1).setResponseMessage(username + " -> User authenticated...");
		}
		else {
			// return Failure response
			response.setResponseCode(99).setResponseMessage(username + " -> User credentials are invalid...");
		}

		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}




	@Override
	public void logout(LogoutRequest request, StreamObserver<LogoutResponse> responseObserver) {
		System.out.println("Attempting to logout user...");
		String username = request.getUsername();

		LogoutResponse.Builder response = LogoutResponse.newBuilder();
		System.out.println("Logging out username = " + username);

		if(username.equals("Cian")) {
			// return Success response
			response.setResponseCode(1).setResponseMessage(username + ".....Successfully logged out");
		}
		else {
			// return Success response
			response.setResponseCode(99).setResponseMessage(username +
					"... Sorry Logout Failed, user not logged in: " + username);
		}

		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

}
