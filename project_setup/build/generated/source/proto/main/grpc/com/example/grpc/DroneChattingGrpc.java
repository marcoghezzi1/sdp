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
      com.example.grpc.DroneChattingOuterClass.Response> getDiscoverMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "discover",
      requestType = com.example.grpc.DroneChattingOuterClass.Request.class,
      responseType = com.example.grpc.DroneChattingOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.Request,
      com.example.grpc.DroneChattingOuterClass.Response> getDiscoverMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.Request, com.example.grpc.DroneChattingOuterClass.Response> getDiscoverMethod;
    if ((getDiscoverMethod = DroneChattingGrpc.getDiscoverMethod) == null) {
      synchronized (DroneChattingGrpc.class) {
        if ((getDiscoverMethod = DroneChattingGrpc.getDiscoverMethod) == null) {
          DroneChattingGrpc.getDiscoverMethod = getDiscoverMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.DroneChattingOuterClass.Request, com.example.grpc.DroneChattingOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "discover"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneChattingMethodDescriptorSupplier("discover"))
              .build();
        }
      }
    }
    return getDiscoverMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.Ping,
      com.example.grpc.DroneChattingOuterClass.Ping> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = com.example.grpc.DroneChattingOuterClass.Ping.class,
      responseType = com.example.grpc.DroneChattingOuterClass.Ping.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.Ping,
      com.example.grpc.DroneChattingOuterClass.Ping> getPingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.Ping, com.example.grpc.DroneChattingOuterClass.Ping> getPingMethod;
    if ((getPingMethod = DroneChattingGrpc.getPingMethod) == null) {
      synchronized (DroneChattingGrpc.class) {
        if ((getPingMethod = DroneChattingGrpc.getPingMethod) == null) {
          DroneChattingGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.DroneChattingOuterClass.Ping, com.example.grpc.DroneChattingOuterClass.Ping>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.Ping.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.Ping.getDefaultInstance()))
              .setSchemaDescriptor(new DroneChattingMethodDescriptorSupplier("ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.ElectionMessage,
      com.example.grpc.DroneChattingOuterClass.ElectionMessage> getElectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "election",
      requestType = com.example.grpc.DroneChattingOuterClass.ElectionMessage.class,
      responseType = com.example.grpc.DroneChattingOuterClass.ElectionMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.ElectionMessage,
      com.example.grpc.DroneChattingOuterClass.ElectionMessage> getElectionMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.ElectionMessage, com.example.grpc.DroneChattingOuterClass.ElectionMessage> getElectionMethod;
    if ((getElectionMethod = DroneChattingGrpc.getElectionMethod) == null) {
      synchronized (DroneChattingGrpc.class) {
        if ((getElectionMethod = DroneChattingGrpc.getElectionMethod) == null) {
          DroneChattingGrpc.getElectionMethod = getElectionMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.DroneChattingOuterClass.ElectionMessage, com.example.grpc.DroneChattingOuterClass.ElectionMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "election"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.ElectionMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.ElectionMessage.getDefaultInstance()))
              .setSchemaDescriptor(new DroneChattingMethodDescriptorSupplier("election"))
              .build();
        }
      }
    }
    return getElectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.ElectionMessage,
      com.example.grpc.DroneChattingOuterClass.ElectionMessage> getElectedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "elected",
      requestType = com.example.grpc.DroneChattingOuterClass.ElectionMessage.class,
      responseType = com.example.grpc.DroneChattingOuterClass.ElectionMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.ElectionMessage,
      com.example.grpc.DroneChattingOuterClass.ElectionMessage> getElectedMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.DroneChattingOuterClass.ElectionMessage, com.example.grpc.DroneChattingOuterClass.ElectionMessage> getElectedMethod;
    if ((getElectedMethod = DroneChattingGrpc.getElectedMethod) == null) {
      synchronized (DroneChattingGrpc.class) {
        if ((getElectedMethod = DroneChattingGrpc.getElectedMethod) == null) {
          DroneChattingGrpc.getElectedMethod = getElectedMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.DroneChattingOuterClass.ElectionMessage, com.example.grpc.DroneChattingOuterClass.ElectionMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "elected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.ElectionMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.DroneChattingOuterClass.ElectionMessage.getDefaultInstance()))
              .setSchemaDescriptor(new DroneChattingMethodDescriptorSupplier("elected"))
              .build();
        }
      }
    }
    return getElectedMethod;
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
    public void discover(com.example.grpc.DroneChattingOuterClass.Request request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getDiscoverMethod(), responseObserver);
    }

    /**
     */
    public void ping(com.example.grpc.DroneChattingOuterClass.Ping request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Ping> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void election(com.example.grpc.DroneChattingOuterClass.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.ElectionMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getElectionMethod(), responseObserver);
    }

    /**
     */
    public void elected(com.example.grpc.DroneChattingOuterClass.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.ElectionMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getElectedMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDiscoverMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.DroneChattingOuterClass.Request,
                com.example.grpc.DroneChattingOuterClass.Response>(
                  this, METHODID_DISCOVER)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.DroneChattingOuterClass.Ping,
                com.example.grpc.DroneChattingOuterClass.Ping>(
                  this, METHODID_PING)))
          .addMethod(
            getElectionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.DroneChattingOuterClass.ElectionMessage,
                com.example.grpc.DroneChattingOuterClass.ElectionMessage>(
                  this, METHODID_ELECTION)))
          .addMethod(
            getElectedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.DroneChattingOuterClass.ElectionMessage,
                com.example.grpc.DroneChattingOuterClass.ElectionMessage>(
                  this, METHODID_ELECTED)))
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
    public void discover(com.example.grpc.DroneChattingOuterClass.Request request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDiscoverMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(com.example.grpc.DroneChattingOuterClass.Ping request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Ping> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void election(com.example.grpc.DroneChattingOuterClass.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.ElectionMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void elected(com.example.grpc.DroneChattingOuterClass.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.ElectionMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request, responseObserver);
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
    public com.example.grpc.DroneChattingOuterClass.Response discover(com.example.grpc.DroneChattingOuterClass.Request request) {
      return blockingUnaryCall(
          getChannel(), getDiscoverMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.DroneChattingOuterClass.Ping ping(com.example.grpc.DroneChattingOuterClass.Ping request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.DroneChattingOuterClass.ElectionMessage election(com.example.grpc.DroneChattingOuterClass.ElectionMessage request) {
      return blockingUnaryCall(
          getChannel(), getElectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.DroneChattingOuterClass.ElectionMessage elected(com.example.grpc.DroneChattingOuterClass.ElectionMessage request) {
      return blockingUnaryCall(
          getChannel(), getElectedMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.DroneChattingOuterClass.Response> discover(
        com.example.grpc.DroneChattingOuterClass.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getDiscoverMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.DroneChattingOuterClass.Ping> ping(
        com.example.grpc.DroneChattingOuterClass.Ping request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.DroneChattingOuterClass.ElectionMessage> election(
        com.example.grpc.DroneChattingOuterClass.ElectionMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.DroneChattingOuterClass.ElectionMessage> elected(
        com.example.grpc.DroneChattingOuterClass.ElectionMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DISCOVER = 0;
  private static final int METHODID_PING = 1;
  private static final int METHODID_ELECTION = 2;
  private static final int METHODID_ELECTED = 3;

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
        case METHODID_DISCOVER:
          serviceImpl.discover((com.example.grpc.DroneChattingOuterClass.Request) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Response>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((com.example.grpc.DroneChattingOuterClass.Ping) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.Ping>) responseObserver);
          break;
        case METHODID_ELECTION:
          serviceImpl.election((com.example.grpc.DroneChattingOuterClass.ElectionMessage) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.ElectionMessage>) responseObserver);
          break;
        case METHODID_ELECTED:
          serviceImpl.elected((com.example.grpc.DroneChattingOuterClass.ElectionMessage) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.DroneChattingOuterClass.ElectionMessage>) responseObserver);
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
              .addMethod(getDiscoverMethod())
              .addMethod(getPingMethod())
              .addMethod(getElectionMethod())
              .addMethod(getElectedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
