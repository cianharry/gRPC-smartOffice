package grpc.services.user;

import java.io.IOException;

import grpc.services.user.UserServiceGrpc.UserServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class UserServer extends UserServiceImplBase{

	public static void main(String[] args) throws IOException, InterruptedException {
		// configuration of the auth server
		System.out.println("Starting gRPC User Auth Server");
		UserServer userserver = new UserServer();

		int port = 50051;

		try {
			Server server = ServerBuilder.forPort(port)
					.addService(userserver)
					.build()
					.start();

			System.out.println("Server started with Port:" + server.getPort());
		    server.awaitTermination();

		}// error handling
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
		System.out.println("Authenticating user credentials");
		String username = request.getUsername();
		String password = request.getPassword();

		LoginResponse.Builder response = LoginResponse.newBuilder();
		System.out.println("username = " + username + ", password = " + password);

		if(username.equals("Cian") && password.equals("Dublin")) {
			// return Success response
			response.setResponseCode(1).setResponseMessage(username + "User authenticated...");
		}
		else {
			// return Failure response
			response.setResponseCode(99).setResponseMessage(username + "User credentials are invalid...");
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

}//class
