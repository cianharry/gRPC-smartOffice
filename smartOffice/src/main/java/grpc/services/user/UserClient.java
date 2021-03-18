package grpc.services.user;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UserClient {

	private static UserServiceGrpc.UserServiceBlockingStub blockingStub;

	public static void main(String[] args) {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		blockingStub = UserServiceGrpc.newBlockingStub(channel);


		login();
		logout();
	}

	//Login
	public static void login() {
		System.out.println("Inside Login Client: ");

		LoginRequest loginRequest = LoginRequest.newBuilder().setUsername("Cian").setPassword("Dublin").build();

		LoginResponse response = blockingStub.login(loginRequest);

		System.out.println(response);
	}

	//Logout
	public static void logout() {
		System.out.println("Inside Logout Client: ");

		LogoutRequest logoutRequest = LogoutRequest.newBuilder().setUsername("Cian").build();

		LogoutResponse response = blockingStub.logout(logoutRequest);

		System.out.println(response);
	}
}
