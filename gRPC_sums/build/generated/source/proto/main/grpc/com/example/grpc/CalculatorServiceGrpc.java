package com.example.grpc;

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
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: CalculatorService.proto")
public final class CalculatorServiceGrpc {

  private CalculatorServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.CalculatorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
      com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getSimpleSumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "simpleSum",
      requestType = com.example.grpc.CalculatorServiceOuterClass.HelloRequest.class,
      responseType = com.example.grpc.CalculatorServiceOuterClass.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
      com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getSimpleSumMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest, com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getSimpleSumMethod;
    if ((getSimpleSumMethod = CalculatorServiceGrpc.getSimpleSumMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getSimpleSumMethod = CalculatorServiceGrpc.getSimpleSumMethod) == null) {
          CalculatorServiceGrpc.getSimpleSumMethod = getSimpleSumMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.CalculatorServiceOuterClass.HelloRequest, com.example.grpc.CalculatorServiceOuterClass.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "simpleSum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CalculatorServiceOuterClass.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CalculatorServiceOuterClass.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("simpleSum"))
              .build();
        }
      }
    }
    return getSimpleSumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
      com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getRepeatedSumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "repeatedSum",
      requestType = com.example.grpc.CalculatorServiceOuterClass.HelloRequest.class,
      responseType = com.example.grpc.CalculatorServiceOuterClass.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
      com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getRepeatedSumMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest, com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getRepeatedSumMethod;
    if ((getRepeatedSumMethod = CalculatorServiceGrpc.getRepeatedSumMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getRepeatedSumMethod = CalculatorServiceGrpc.getRepeatedSumMethod) == null) {
          CalculatorServiceGrpc.getRepeatedSumMethod = getRepeatedSumMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.CalculatorServiceOuterClass.HelloRequest, com.example.grpc.CalculatorServiceOuterClass.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "repeatedSum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CalculatorServiceOuterClass.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CalculatorServiceOuterClass.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("repeatedSum"))
              .build();
        }
      }
    }
    return getRepeatedSumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
      com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getStreamSumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "streamSum",
      requestType = com.example.grpc.CalculatorServiceOuterClass.HelloRequest.class,
      responseType = com.example.grpc.CalculatorServiceOuterClass.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
      com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getStreamSumMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.CalculatorServiceOuterClass.HelloRequest, com.example.grpc.CalculatorServiceOuterClass.HelloResponse> getStreamSumMethod;
    if ((getStreamSumMethod = CalculatorServiceGrpc.getStreamSumMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getStreamSumMethod = CalculatorServiceGrpc.getStreamSumMethod) == null) {
          CalculatorServiceGrpc.getStreamSumMethod = getStreamSumMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.CalculatorServiceOuterClass.HelloRequest, com.example.grpc.CalculatorServiceOuterClass.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "streamSum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CalculatorServiceOuterClass.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CalculatorServiceOuterClass.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("streamSum"))
              .build();
        }
      }
    }
    return getStreamSumMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalculatorServiceStub newStub(io.grpc.Channel channel) {
    return new CalculatorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalculatorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CalculatorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CalculatorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CalculatorServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CalculatorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void simpleSum(com.example.grpc.CalculatorServiceOuterClass.HelloRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSimpleSumMethod(), responseObserver);
    }

    /**
     */
    public void repeatedSum(com.example.grpc.CalculatorServiceOuterClass.HelloRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRepeatedSumMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloRequest> streamSum(
        io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getStreamSumMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSimpleSumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
                com.example.grpc.CalculatorServiceOuterClass.HelloResponse>(
                  this, METHODID_SIMPLE_SUM)))
          .addMethod(
            getRepeatedSumMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
                com.example.grpc.CalculatorServiceOuterClass.HelloResponse>(
                  this, METHODID_REPEATED_SUM)))
          .addMethod(
            getStreamSumMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.example.grpc.CalculatorServiceOuterClass.HelloRequest,
                com.example.grpc.CalculatorServiceOuterClass.HelloResponse>(
                  this, METHODID_STREAM_SUM)))
          .build();
    }
  }

  /**
   */
  public static final class CalculatorServiceStub extends io.grpc.stub.AbstractStub<CalculatorServiceStub> {
    private CalculatorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceStub(channel, callOptions);
    }

    /**
     */
    public void simpleSum(com.example.grpc.CalculatorServiceOuterClass.HelloRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSimpleSumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void repeatedSum(com.example.grpc.CalculatorServiceOuterClass.HelloRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getRepeatedSumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloRequest> streamSum(
        io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getStreamSumMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class CalculatorServiceBlockingStub extends io.grpc.stub.AbstractStub<CalculatorServiceBlockingStub> {
    private CalculatorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.CalculatorServiceOuterClass.HelloResponse simpleSum(com.example.grpc.CalculatorServiceOuterClass.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSimpleSumMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.example.grpc.CalculatorServiceOuterClass.HelloResponse> repeatedSum(
        com.example.grpc.CalculatorServiceOuterClass.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getRepeatedSumMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CalculatorServiceFutureStub extends io.grpc.stub.AbstractStub<CalculatorServiceFutureStub> {
    private CalculatorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.CalculatorServiceOuterClass.HelloResponse> simpleSum(
        com.example.grpc.CalculatorServiceOuterClass.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSimpleSumMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SIMPLE_SUM = 0;
  private static final int METHODID_REPEATED_SUM = 1;
  private static final int METHODID_STREAM_SUM = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CalculatorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CalculatorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SIMPLE_SUM:
          serviceImpl.simpleSum((com.example.grpc.CalculatorServiceOuterClass.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse>) responseObserver);
          break;
        case METHODID_REPEATED_SUM:
          serviceImpl.repeatedSum((com.example.grpc.CalculatorServiceOuterClass.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse>) responseObserver);
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
        case METHODID_STREAM_SUM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamSum(
              (io.grpc.stub.StreamObserver<com.example.grpc.CalculatorServiceOuterClass.HelloResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CalculatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CalculatorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.CalculatorServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CalculatorService");
    }
  }

  private static final class CalculatorServiceFileDescriptorSupplier
      extends CalculatorServiceBaseDescriptorSupplier {
    CalculatorServiceFileDescriptorSupplier() {}
  }

  private static final class CalculatorServiceMethodDescriptorSupplier
      extends CalculatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CalculatorServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CalculatorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalculatorServiceFileDescriptorSupplier())
              .addMethod(getSimpleSumMethod())
              .addMethod(getRepeatedSumMethod())
              .addMethod(getStreamSumMethod())
              .build();
        }
      }
    }
    return result;
  }
}
