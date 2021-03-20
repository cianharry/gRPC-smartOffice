package grpc.services.news;

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
    comments = "Source: grpcNews.proto")
public final class NewsServiceGrpc {

  private NewsServiceGrpc() {}

  public static final String SERVICE_NAME = "news.NewsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.news.NewsPowerRequest,
      grpc.services.news.NewsPowerResponse> getSwitchNewsPowerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "switchNewsPower",
      requestType = grpc.services.news.NewsPowerRequest.class,
      responseType = grpc.services.news.NewsPowerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.news.NewsPowerRequest,
      grpc.services.news.NewsPowerResponse> getSwitchNewsPowerMethod() {
    io.grpc.MethodDescriptor<grpc.services.news.NewsPowerRequest, grpc.services.news.NewsPowerResponse> getSwitchNewsPowerMethod;
    if ((getSwitchNewsPowerMethod = NewsServiceGrpc.getSwitchNewsPowerMethod) == null) {
      synchronized (NewsServiceGrpc.class) {
        if ((getSwitchNewsPowerMethod = NewsServiceGrpc.getSwitchNewsPowerMethod) == null) {
          NewsServiceGrpc.getSwitchNewsPowerMethod = getSwitchNewsPowerMethod = 
              io.grpc.MethodDescriptor.<grpc.services.news.NewsPowerRequest, grpc.services.news.NewsPowerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "news.NewsService", "switchNewsPower"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.news.NewsPowerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.news.NewsPowerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new NewsServiceMethodDescriptorSupplier("switchNewsPower"))
                  .build();
          }
        }
     }
     return getSwitchNewsPowerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.news.NewsStreamRequest,
      grpc.services.news.NewsStreamResponse> getStreamNewsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "streamNews",
      requestType = grpc.services.news.NewsStreamRequest.class,
      responseType = grpc.services.news.NewsStreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.news.NewsStreamRequest,
      grpc.services.news.NewsStreamResponse> getStreamNewsMethod() {
    io.grpc.MethodDescriptor<grpc.services.news.NewsStreamRequest, grpc.services.news.NewsStreamResponse> getStreamNewsMethod;
    if ((getStreamNewsMethod = NewsServiceGrpc.getStreamNewsMethod) == null) {
      synchronized (NewsServiceGrpc.class) {
        if ((getStreamNewsMethod = NewsServiceGrpc.getStreamNewsMethod) == null) {
          NewsServiceGrpc.getStreamNewsMethod = getStreamNewsMethod = 
              io.grpc.MethodDescriptor.<grpc.services.news.NewsStreamRequest, grpc.services.news.NewsStreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "news.NewsService", "streamNews"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.news.NewsStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.news.NewsStreamResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new NewsServiceMethodDescriptorSupplier("streamNews"))
                  .build();
          }
        }
     }
     return getStreamNewsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NewsServiceStub newStub(io.grpc.Channel channel) {
    return new NewsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NewsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NewsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NewsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NewsServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NewsServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public void switchNewsPower(grpc.services.news.NewsPowerRequest request,
        io.grpc.stub.StreamObserver<grpc.services.news.NewsPowerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchNewsPowerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bidirectional streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.services.news.NewsStreamRequest> streamNews(
        io.grpc.stub.StreamObserver<grpc.services.news.NewsStreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getStreamNewsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSwitchNewsPowerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.news.NewsPowerRequest,
                grpc.services.news.NewsPowerResponse>(
                  this, METHODID_SWITCH_NEWS_POWER)))
          .addMethod(
            getStreamNewsMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.services.news.NewsStreamRequest,
                grpc.services.news.NewsStreamResponse>(
                  this, METHODID_STREAM_NEWS)))
          .build();
    }
  }

  /**
   */
  public static final class NewsServiceStub extends io.grpc.stub.AbstractStub<NewsServiceStub> {
    private NewsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewsServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public void switchNewsPower(grpc.services.news.NewsPowerRequest request,
        io.grpc.stub.StreamObserver<grpc.services.news.NewsPowerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchNewsPowerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bidirectional streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.services.news.NewsStreamRequest> streamNews(
        io.grpc.stub.StreamObserver<grpc.services.news.NewsStreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getStreamNewsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class NewsServiceBlockingStub extends io.grpc.stub.AbstractStub<NewsServiceBlockingStub> {
    private NewsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewsServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public grpc.services.news.NewsPowerResponse switchNewsPower(grpc.services.news.NewsPowerRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwitchNewsPowerMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NewsServiceFutureStub extends io.grpc.stub.AbstractStub<NewsServiceFutureStub> {
    private NewsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewsServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.news.NewsPowerResponse> switchNewsPower(
        grpc.services.news.NewsPowerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchNewsPowerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SWITCH_NEWS_POWER = 0;
  private static final int METHODID_STREAM_NEWS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NewsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NewsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SWITCH_NEWS_POWER:
          serviceImpl.switchNewsPower((grpc.services.news.NewsPowerRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.news.NewsPowerResponse>) responseObserver);
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
        case METHODID_STREAM_NEWS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamNews(
              (io.grpc.stub.StreamObserver<grpc.services.news.NewsStreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NewsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NewsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.news.NewsServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NewsService");
    }
  }

  private static final class NewsServiceFileDescriptorSupplier
      extends NewsServiceBaseDescriptorSupplier {
    NewsServiceFileDescriptorSupplier() {}
  }

  private static final class NewsServiceMethodDescriptorSupplier
      extends NewsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NewsServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (NewsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NewsServiceFileDescriptorSupplier())
              .addMethod(getSwitchNewsPowerMethod())
              .addMethod(getStreamNewsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
