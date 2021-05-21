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
    comments = "Source: DroneChatting.proto")
public final class DroneChattingGrpc {

  private DroneChattingGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.DroneChatting";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.Request,
      com.example.grpc.DroneChattingOuterClass.Response> getChattingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "chatting",
      requestType = com.example.grpc.DroneChattingOuterClass.Request.class,
      responseType = com.example.grpc.DroneChattingOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.Request,
      com.example.grpc.DroneChattingOuterClass.Response> getChattingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.Request, com.example.grpc.DroneChattingOuterClass.Response> getChattingMethod;
    if ((getChattingMethod = DroneChattingGrpc.getChattingMethod) == null) {
      synchronized (DroneChattingGrpc.class) {
        if ((getChattingMethod = DroneChattingGrpc.getChattingMethod) == null) {
          DroneChattingGrpc.getChattingMethod = getChattingMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.DroneChattingOuterClass.Request, com.example.grpc.DroneChattingOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "chatting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneChattingMethodDescriptorSupplier("chatting"))
              .build();
        }
      }
    }
    return getChattingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DroneChattingStub newStub(io.grpc.Channel channel) {
    return new DroneChattingStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DroneChattingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DroneChattingBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DroneChattingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DroneChattingFutureStub(channel);
  }

  /**
   */
  public static abstract class DroneChattingImplBase implements io.grpc.BindableService {

    /**
     */
    public void chatting(com.example.grpc.DroneChattingOuterClass.Request request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getChattingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getChattingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.DroneChattingOuterClass.Request,
                com.example.grpc.DroneChattingOuterClass.Response>(
                  this, METHODID_CHATTING)))
          .build();
    }
  }

  /**
   */
  public static final class DroneChattingStub extends io.grpc.stub.AbstractStub<DroneChattingStub> {
    private DroneChattingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneChattingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneChattingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneChattingStub(channel, callOptions);
    }

    /**
     */
    public void chatting(com.example.grpc.DroneChattingOuterClass.Request request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChattingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DroneChattingBlockingStub extends io.grpc.stub.AbstractStub<DroneChattingBlockingStub> {
    private DroneChattingBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneChattingBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneChattingBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneChattingBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.DroneChattingOuterClass.Response chatting(com.example.grpc.DroneChattingOuterClass.Request request) {
      return blockingUnaryCall(
          getChannel(), getChattingMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DroneChattingFutureStub extends io.grpc.stub.AbstractStub<DroneChattingFutureStub> {
    private DroneChattingFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneChattingFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneChattingFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneChattingFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.DroneChattingOuterClass.Response> chatting(
        com.example.grpc.DroneChattingOuterClass.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getChattingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHATTING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DroneChattingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DroneChattingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHATTING:
          serviceImpl.chatting((com.example.grpc.DroneChattingOuterClass.Request) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Response>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DroneChattingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DroneChattingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.DroneChattingOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DroneChatting");
    }
  }

  private static final class DroneChattingFileDescriptorSupplier
      extends DroneChattingBaseDescriptorSupplier {
    DroneChattingFileDescriptorSupplier() {}
  }

  private static final class DroneChattingMethodDescriptorSupplier
      extends DroneChattingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DroneChattingMethodDescriptorSupplier(String methodName) {
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
      synchronized (DroneChattingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DroneChattingFileDescriptorSupplier())
              .addMethod(getChattingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
