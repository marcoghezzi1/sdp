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
    comments = "Source: Election.proto")
public final class ElezioneGrpc {

  private ElezioneGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.Elezione";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.Election.ElectionMessage,
      com.example.grpc.Election.ElectionMessage> getElectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "election",
      requestType = com.example.grpc.Election.ElectionMessage.class,
      responseType = com.example.grpc.Election.ElectionMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.Election.ElectionMessage,
      com.example.grpc.Election.ElectionMessage> getElectionMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.Election.ElectionMessage, com.example.grpc.Election.ElectionMessage> getElectionMethod;
    if ((getElectionMethod = ElezioneGrpc.getElectionMethod) == null) {
      synchronized (ElezioneGrpc.class) {
        if ((getElectionMethod = ElezioneGrpc.getElectionMethod) == null) {
          ElezioneGrpc.getElectionMethod = getElectionMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.Election.ElectionMessage, com.example.grpc.Election.ElectionMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "election"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.Election.ElectionMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.Election.ElectionMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ElezioneMethodDescriptorSupplier("election"))
              .build();
        }
      }
    }
    return getElectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.Election.ElectionMessage,
      com.example.grpc.Election.ElectionMessage> getElectedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "elected",
      requestType = com.example.grpc.Election.ElectionMessage.class,
      responseType = com.example.grpc.Election.ElectionMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.Election.ElectionMessage,
      com.example.grpc.Election.ElectionMessage> getElectedMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.Election.ElectionMessage, com.example.grpc.Election.ElectionMessage> getElectedMethod;
    if ((getElectedMethod = ElezioneGrpc.getElectedMethod) == null) {
      synchronized (ElezioneGrpc.class) {
        if ((getElectedMethod = ElezioneGrpc.getElectedMethod) == null) {
          ElezioneGrpc.getElectedMethod = getElectedMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.Election.ElectionMessage, com.example.grpc.Election.ElectionMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "elected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.Election.ElectionMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.Election.ElectionMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ElezioneMethodDescriptorSupplier("elected"))
              .build();
        }
      }
    }
    return getElectedMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ElezioneStub newStub(io.grpc.Channel channel) {
    return new ElezioneStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ElezioneBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ElezioneBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ElezioneFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ElezioneFutureStub(channel);
  }

  /**
   */
  public static abstract class ElezioneImplBase implements io.grpc.BindableService {

    /**
     */
    public void election(com.example.grpc.Election.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.Election.ElectionMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getElectionMethod(), responseObserver);
    }

    /**
     */
    public void elected(com.example.grpc.Election.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.Election.ElectionMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getElectedMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getElectionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.Election.ElectionMessage,
                com.example.grpc.Election.ElectionMessage>(
                  this, METHODID_ELECTION)))
          .addMethod(
            getElectedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.Election.ElectionMessage,
                com.example.grpc.Election.ElectionMessage>(
                  this, METHODID_ELECTED)))
          .build();
    }
  }

  /**
   */
  public static final class ElezioneStub extends io.grpc.stub.AbstractStub<ElezioneStub> {
    private ElezioneStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElezioneStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElezioneStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElezioneStub(channel, callOptions);
    }

    /**
     */
    public void election(com.example.grpc.Election.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.Election.ElectionMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void elected(com.example.grpc.Election.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.Election.ElectionMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ElezioneBlockingStub extends io.grpc.stub.AbstractStub<ElezioneBlockingStub> {
    private ElezioneBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElezioneBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElezioneBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElezioneBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.Election.ElectionMessage election(com.example.grpc.Election.ElectionMessage request) {
      return blockingUnaryCall(
          getChannel(), getElectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.Election.ElectionMessage elected(com.example.grpc.Election.ElectionMessage request) {
      return blockingUnaryCall(
          getChannel(), getElectedMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ElezioneFutureStub extends io.grpc.stub.AbstractStub<ElezioneFutureStub> {
    private ElezioneFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElezioneFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElezioneFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElezioneFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.Election.ElectionMessage> election(
        com.example.grpc.Election.ElectionMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.Election.ElectionMessage> elected(
        com.example.grpc.Election.ElectionMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ELECTION = 0;
  private static final int METHODID_ELECTED = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ElezioneImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ElezioneImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ELECTION:
          serviceImpl.election((com.example.grpc.Election.ElectionMessage) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.Election.ElectionMessage>) responseObserver);
          break;
        case METHODID_ELECTED:
          serviceImpl.elected((com.example.grpc.Election.ElectionMessage) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.Election.ElectionMessage>) responseObserver);
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

  private static abstract class ElezioneBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ElezioneBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.Election.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Elezione");
    }
  }

  private static final class ElezioneFileDescriptorSupplier
      extends ElezioneBaseDescriptorSupplier {
    ElezioneFileDescriptorSupplier() {}
  }

  private static final class ElezioneMethodDescriptorSupplier
      extends ElezioneBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ElezioneMethodDescriptorSupplier(String methodName) {
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
      synchronized (ElezioneGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ElezioneFileDescriptorSupplier())
              .addMethod(getElectionMethod())
              .addMethod(getElectedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
