package grpc.services.utilities;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: grpcUtilities.proto")
public final class UtilitiesServiceGrpc {

  private UtilitiesServiceGrpc() {}

  public static final String SERVICE_NAME = "utilities.UtilitiesService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.utilities.LightPowerRequest,
      grpc.services.utilities.LightPowerResponse> getSwitchLightPowerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "switchLightPower",
      requestType = grpc.services.utilities.LightPowerRequest.class,
      responseType = grpc.services.utilities.LightPowerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.utilities.LightPowerRequest,
      grpc.services.utilities.LightPowerResponse> getSwitchLightPowerMethod() {
    io.grpc.MethodDescriptor<grpc.services.utilities.LightPowerRequest, grpc.services.utilities.LightPowerResponse> getSwitchLightPowerMethod;
    if ((getSwitchLightPowerMethod = UtilitiesServiceGrpc.getSwitchLightPowerMethod) == null) {
      synchronized (UtilitiesServiceGrpc.class) {
        if ((getSwitchLightPowerMethod = UtilitiesServiceGrpc.getSwitchLightPowerMethod) == null) {
          UtilitiesServiceGrpc.getSwitchLightPowerMethod = getSwitchLightPowerMethod = 
              io.grpc.MethodDescriptor.<grpc.services.utilities.LightPowerRequest, grpc.services.utilities.LightPowerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "utilities.UtilitiesService", "switchLightPower"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utilities.LightPowerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utilities.LightPowerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UtilitiesServiceMethodDescriptorSupplier("switchLightPower"))
                  .build();
          }
        }
     }
     return getSwitchLightPowerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.utilities.LightSettingRequest,
      grpc.services.utilities.LightSettingResponse> getAdjustLightSettingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "adjustLightSetting",
      requestType = grpc.services.utilities.LightSettingRequest.class,
      responseType = grpc.services.utilities.LightSettingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.utilities.LightSettingRequest,
      grpc.services.utilities.LightSettingResponse> getAdjustLightSettingMethod() {
    io.grpc.MethodDescriptor<grpc.services.utilities.LightSettingRequest, grpc.services.utilities.LightSettingResponse> getAdjustLightSettingMethod;
    if ((getAdjustLightSettingMethod = UtilitiesServiceGrpc.getAdjustLightSettingMethod) == null) {
      synchronized (UtilitiesServiceGrpc.class) {
        if ((getAdjustLightSettingMethod = UtilitiesServiceGrpc.getAdjustLightSettingMethod) == null) {
          UtilitiesServiceGrpc.getAdjustLightSettingMethod = getAdjustLightSettingMethod = 
              io.grpc.MethodDescriptor.<grpc.services.utilities.LightSettingRequest, grpc.services.utilities.LightSettingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "utilities.UtilitiesService", "adjustLightSetting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utilities.LightSettingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utilities.LightSettingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UtilitiesServiceMethodDescriptorSupplier("adjustLightSetting"))
                  .build();
          }
        }
     }
     return getAdjustLightSettingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.utilities.HeatPowerRequest,
      grpc.services.utilities.HeatPowerResponse> getSwitchHeatPowerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "switchHeatPower",
      requestType = grpc.services.utilities.HeatPowerRequest.class,
      responseType = grpc.services.utilities.HeatPowerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.utilities.HeatPowerRequest,
      grpc.services.utilities.HeatPowerResponse> getSwitchHeatPowerMethod() {
    io.grpc.MethodDescriptor<grpc.services.utilities.HeatPowerRequest, grpc.services.utilities.HeatPowerResponse> getSwitchHeatPowerMethod;
    if ((getSwitchHeatPowerMethod = UtilitiesServiceGrpc.getSwitchHeatPowerMethod) == null) {
      synchronized (UtilitiesServiceGrpc.class) {
        if ((getSwitchHeatPowerMethod = UtilitiesServiceGrpc.getSwitchHeatPowerMethod) == null) {
          UtilitiesServiceGrpc.getSwitchHeatPowerMethod = getSwitchHeatPowerMethod = 
              io.grpc.MethodDescriptor.<grpc.services.utilities.HeatPowerRequest, grpc.services.utilities.HeatPowerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "utilities.UtilitiesService", "switchHeatPower"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utilities.HeatPowerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utilities.HeatPowerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UtilitiesServiceMethodDescriptorSupplier("switchHeatPower"))
                  .build();
          }
        }
     }
     return getSwitchHeatPowerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.utilities.HeatTempRequest,
      grpc.services.utilities.HeatTempResponse> getSelectHeatTempMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "selectHeatTemp",
      requestType = grpc.services.utilities.HeatTempRequest.class,
      responseType = grpc.services.utilities.HeatTempResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.utilities.HeatTempRequest,
      grpc.services.utilities.HeatTempResponse> getSelectHeatTempMethod() {
    io.grpc.MethodDescriptor<grpc.services.utilities.HeatTempRequest, grpc.services.utilities.HeatTempResponse> getSelectHeatTempMethod;
    if ((getSelectHeatTempMethod = UtilitiesServiceGrpc.getSelectHeatTempMethod) == null) {
      synchronized (UtilitiesServiceGrpc.class) {
        if ((getSelectHeatTempMethod = UtilitiesServiceGrpc.getSelectHeatTempMethod) == null) {
          UtilitiesServiceGrpc.getSelectHeatTempMethod = getSelectHeatTempMethod = 
              io.grpc.MethodDescriptor.<grpc.services.utilities.HeatTempRequest, grpc.services.utilities.HeatTempResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "utilities.UtilitiesService", "selectHeatTemp"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utilities.HeatTempRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utilities.HeatTempResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UtilitiesServiceMethodDescriptorSupplier("selectHeatTemp"))
                  .build();
          }
        }
     }
     return getSelectHeatTempMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UtilitiesServiceStub newStub(io.grpc.Channel channel) {
    return new UtilitiesServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UtilitiesServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UtilitiesServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UtilitiesServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UtilitiesServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class UtilitiesServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public void switchLightPower(grpc.services.utilities.LightPowerRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utilities.LightPowerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchLightPowerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Client streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.services.utilities.LightSettingRequest> adjustLightSetting(
        io.grpc.stub.StreamObserver<grpc.services.utilities.LightSettingResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getAdjustLightSettingMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public void switchHeatPower(grpc.services.utilities.HeatPowerRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utilities.HeatPowerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchHeatPowerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server streaming RPC
     * </pre>
     */
    public void selectHeatTemp(grpc.services.utilities.HeatTempRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utilities.HeatTempResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSelectHeatTempMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSwitchLightPowerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.utilities.LightPowerRequest,
                grpc.services.utilities.LightPowerResponse>(
                  this, METHODID_SWITCH_LIGHT_POWER)))
          .addMethod(
            getAdjustLightSettingMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.services.utilities.LightSettingRequest,
                grpc.services.utilities.LightSettingResponse>(
                  this, METHODID_ADJUST_LIGHT_SETTING)))
          .addMethod(
            getSwitchHeatPowerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.utilities.HeatPowerRequest,
                grpc.services.utilities.HeatPowerResponse>(
                  this, METHODID_SWITCH_HEAT_POWER)))
          .addMethod(
            getSelectHeatTempMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.services.utilities.HeatTempRequest,
                grpc.services.utilities.HeatTempResponse>(
                  this, METHODID_SELECT_HEAT_TEMP)))
          .build();
    }
  }

  /**
   */
  public static final class UtilitiesServiceStub extends io.grpc.stub.AbstractStub<UtilitiesServiceStub> {
    private UtilitiesServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UtilitiesServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UtilitiesServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UtilitiesServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public void switchLightPower(grpc.services.utilities.LightPowerRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utilities.LightPowerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchLightPowerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Client streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.services.utilities.LightSettingRequest> adjustLightSetting(
        io.grpc.stub.StreamObserver<grpc.services.utilities.LightSettingResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getAdjustLightSettingMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public void switchHeatPower(grpc.services.utilities.HeatPowerRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utilities.HeatPowerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchHeatPowerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server streaming RPC
     * </pre>
     */
    public void selectHeatTemp(grpc.services.utilities.HeatTempRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utilities.HeatTempResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSelectHeatTempMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UtilitiesServiceBlockingStub extends io.grpc.stub.AbstractStub<UtilitiesServiceBlockingStub> {
    private UtilitiesServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UtilitiesServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UtilitiesServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UtilitiesServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public grpc.services.utilities.LightPowerResponse switchLightPower(grpc.services.utilities.LightPowerRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwitchLightPowerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public grpc.services.utilities.HeatPowerResponse switchHeatPower(grpc.services.utilities.HeatPowerRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwitchHeatPowerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server streaming RPC
     * </pre>
     */
    public java.util.Iterator<grpc.services.utilities.HeatTempResponse> selectHeatTemp(
        grpc.services.utilities.HeatTempRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSelectHeatTempMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UtilitiesServiceFutureStub extends io.grpc.stub.AbstractStub<UtilitiesServiceFutureStub> {
    private UtilitiesServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UtilitiesServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UtilitiesServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UtilitiesServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.utilities.LightPowerResponse> switchLightPower(
        grpc.services.utilities.LightPowerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchLightPowerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.utilities.HeatPowerResponse> switchHeatPower(
        grpc.services.utilities.HeatPowerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchHeatPowerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SWITCH_LIGHT_POWER = 0;
  private static final int METHODID_SWITCH_HEAT_POWER = 1;
  private static final int METHODID_SELECT_HEAT_TEMP = 2;
  private static final int METHODID_ADJUST_LIGHT_SETTING = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UtilitiesServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UtilitiesServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SWITCH_LIGHT_POWER:
          serviceImpl.switchLightPower((grpc.services.utilities.LightPowerRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.utilities.LightPowerResponse>) responseObserver);
          break;
        case METHODID_SWITCH_HEAT_POWER:
          serviceImpl.switchHeatPower((grpc.services.utilities.HeatPowerRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.utilities.HeatPowerResponse>) responseObserver);
          break;
        case METHODID_SELECT_HEAT_TEMP:
          serviceImpl.selectHeatTemp((grpc.services.utilities.HeatTempRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.utilities.HeatTempResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADJUST_LIGHT_SETTING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.adjustLightSetting(
              (io.grpc.stub.StreamObserver<grpc.services.utilities.LightSettingResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UtilitiesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UtilitiesServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.utilities.UtilitiesServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UtilitiesService");
    }
  }

  private static final class UtilitiesServiceFileDescriptorSupplier
      extends UtilitiesServiceBaseDescriptorSupplier {
    UtilitiesServiceFileDescriptorSupplier() {}
  }

  private static final class UtilitiesServiceMethodDescriptorSupplier
      extends UtilitiesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UtilitiesServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UtilitiesServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UtilitiesServiceFileDescriptorSupplier())
              .addMethod(getSwitchLightPowerMethod())
              .addMethod(getAdjustLightSettingMethod())
              .addMethod(getSwitchHeatPowerMethod())
              .addMethod(getSelectHeatTempMethod())
              .build();
        }
      }
    }
    return result;
  }
}
