package com.vnpt.grpcprotocol;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: AuthGrpcService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthGrpcServiceGrpc {

  private AuthGrpcServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.vnpt.grpc.proto.AuthGrpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.UserRequest,
      com.vnpt.grpcprotocol.UserResponse> getGetUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUser",
      requestType = com.vnpt.grpcprotocol.UserRequest.class,
      responseType = com.vnpt.grpcprotocol.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.UserRequest,
      com.vnpt.grpcprotocol.UserResponse> getGetUserMethod() {
    io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.UserRequest, com.vnpt.grpcprotocol.UserResponse> getGetUserMethod;
    if ((getGetUserMethod = AuthGrpcServiceGrpc.getGetUserMethod) == null) {
      synchronized (AuthGrpcServiceGrpc.class) {
        if ((getGetUserMethod = AuthGrpcServiceGrpc.getGetUserMethod) == null) {
          AuthGrpcServiceGrpc.getGetUserMethod = getGetUserMethod =
              io.grpc.MethodDescriptor.<com.vnpt.grpcprotocol.UserRequest, com.vnpt.grpcprotocol.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vnpt.grpcprotocol.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vnpt.grpcprotocol.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthGrpcServiceMethodDescriptorSupplier("getUser"))
              .build();
        }
      }
    }
    return getGetUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.RoleRequest,
      com.vnpt.grpcprotocol.RoleResponse> getGetRoleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRole",
      requestType = com.vnpt.grpcprotocol.RoleRequest.class,
      responseType = com.vnpt.grpcprotocol.RoleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.RoleRequest,
      com.vnpt.grpcprotocol.RoleResponse> getGetRoleMethod() {
    io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.RoleRequest, com.vnpt.grpcprotocol.RoleResponse> getGetRoleMethod;
    if ((getGetRoleMethod = AuthGrpcServiceGrpc.getGetRoleMethod) == null) {
      synchronized (AuthGrpcServiceGrpc.class) {
        if ((getGetRoleMethod = AuthGrpcServiceGrpc.getGetRoleMethod) == null) {
          AuthGrpcServiceGrpc.getGetRoleMethod = getGetRoleMethod =
              io.grpc.MethodDescriptor.<com.vnpt.grpcprotocol.RoleRequest, com.vnpt.grpcprotocol.RoleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getRole"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vnpt.grpcprotocol.RoleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vnpt.grpcprotocol.RoleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthGrpcServiceMethodDescriptorSupplier("getRole"))
              .build();
        }
      }
    }
    return getGetRoleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.ResourceRequest,
      com.vnpt.grpcprotocol.ResourceResponse> getGetResourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getResource",
      requestType = com.vnpt.grpcprotocol.ResourceRequest.class,
      responseType = com.vnpt.grpcprotocol.ResourceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.ResourceRequest,
      com.vnpt.grpcprotocol.ResourceResponse> getGetResourceMethod() {
    io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.ResourceRequest, com.vnpt.grpcprotocol.ResourceResponse> getGetResourceMethod;
    if ((getGetResourceMethod = AuthGrpcServiceGrpc.getGetResourceMethod) == null) {
      synchronized (AuthGrpcServiceGrpc.class) {
        if ((getGetResourceMethod = AuthGrpcServiceGrpc.getGetResourceMethod) == null) {
          AuthGrpcServiceGrpc.getGetResourceMethod = getGetResourceMethod =
              io.grpc.MethodDescriptor.<com.vnpt.grpcprotocol.ResourceRequest, com.vnpt.grpcprotocol.ResourceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getResource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vnpt.grpcprotocol.ResourceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vnpt.grpcprotocol.ResourceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthGrpcServiceMethodDescriptorSupplier("getResource"))
              .build();
        }
      }
    }
    return getGetResourceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.GetUserInfoByTokenRequest,
      com.vnpt.grpcprotocol.GetUserInfoByTokenResponse> getGetUserByTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserByToken",
      requestType = com.vnpt.grpcprotocol.GetUserInfoByTokenRequest.class,
      responseType = com.vnpt.grpcprotocol.GetUserInfoByTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.GetUserInfoByTokenRequest,
      com.vnpt.grpcprotocol.GetUserInfoByTokenResponse> getGetUserByTokenMethod() {
    io.grpc.MethodDescriptor<com.vnpt.grpcprotocol.GetUserInfoByTokenRequest, com.vnpt.grpcprotocol.GetUserInfoByTokenResponse> getGetUserByTokenMethod;
    if ((getGetUserByTokenMethod = AuthGrpcServiceGrpc.getGetUserByTokenMethod) == null) {
      synchronized (AuthGrpcServiceGrpc.class) {
        if ((getGetUserByTokenMethod = AuthGrpcServiceGrpc.getGetUserByTokenMethod) == null) {
          AuthGrpcServiceGrpc.getGetUserByTokenMethod = getGetUserByTokenMethod =
              io.grpc.MethodDescriptor.<com.vnpt.grpcprotocol.GetUserInfoByTokenRequest, com.vnpt.grpcprotocol.GetUserInfoByTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUserByToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vnpt.grpcprotocol.GetUserInfoByTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vnpt.grpcprotocol.GetUserInfoByTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthGrpcServiceMethodDescriptorSupplier("getUserByToken"))
              .build();
        }
      }
    }
    return getGetUserByTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthGrpcServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthGrpcServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthGrpcServiceStub>() {
        @java.lang.Override
        public AuthGrpcServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthGrpcServiceStub(channel, callOptions);
        }
      };
    return AuthGrpcServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthGrpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthGrpcServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthGrpcServiceBlockingStub>() {
        @java.lang.Override
        public AuthGrpcServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthGrpcServiceBlockingStub(channel, callOptions);
        }
      };
    return AuthGrpcServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthGrpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthGrpcServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthGrpcServiceFutureStub>() {
        @java.lang.Override
        public AuthGrpcServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthGrpcServiceFutureStub(channel, callOptions);
        }
      };
    return AuthGrpcServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getUser(com.vnpt.grpcprotocol.UserRequest request,
        io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.UserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserMethod(), responseObserver);
    }

    /**
     */
    default void getRole(com.vnpt.grpcprotocol.RoleRequest request,
        io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.RoleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRoleMethod(), responseObserver);
    }

    /**
     */
    default void getResource(com.vnpt.grpcprotocol.ResourceRequest request,
        io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.ResourceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetResourceMethod(), responseObserver);
    }

    /**
     */
    default void getUserByToken(com.vnpt.grpcprotocol.GetUserInfoByTokenRequest request,
        io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.GetUserInfoByTokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserByTokenMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AuthGrpcService.
   */
  public static abstract class AuthGrpcServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuthGrpcServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AuthGrpcService.
   */
  public static final class AuthGrpcServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AuthGrpcServiceStub> {
    private AuthGrpcServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthGrpcServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthGrpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void getUser(com.vnpt.grpcprotocol.UserRequest request,
        io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.UserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getRole(com.vnpt.grpcprotocol.RoleRequest request,
        io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.RoleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetRoleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getResource(com.vnpt.grpcprotocol.ResourceRequest request,
        io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.ResourceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetResourceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserByToken(com.vnpt.grpcprotocol.GetUserInfoByTokenRequest request,
        io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.GetUserInfoByTokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserByTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AuthGrpcService.
   */
  public static final class AuthGrpcServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuthGrpcServiceBlockingStub> {
    private AuthGrpcServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthGrpcServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthGrpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.vnpt.grpcprotocol.UserResponse getUser(com.vnpt.grpcprotocol.UserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.vnpt.grpcprotocol.RoleResponse getRole(com.vnpt.grpcprotocol.RoleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetRoleMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.vnpt.grpcprotocol.ResourceResponse getResource(com.vnpt.grpcprotocol.ResourceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetResourceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.vnpt.grpcprotocol.GetUserInfoByTokenResponse getUserByToken(com.vnpt.grpcprotocol.GetUserInfoByTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserByTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AuthGrpcService.
   */
  public static final class AuthGrpcServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuthGrpcServiceFutureStub> {
    private AuthGrpcServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthGrpcServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthGrpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vnpt.grpcprotocol.UserResponse> getUser(
        com.vnpt.grpcprotocol.UserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vnpt.grpcprotocol.RoleResponse> getRole(
        com.vnpt.grpcprotocol.RoleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetRoleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vnpt.grpcprotocol.ResourceResponse> getResource(
        com.vnpt.grpcprotocol.ResourceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetResourceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vnpt.grpcprotocol.GetUserInfoByTokenResponse> getUserByToken(
        com.vnpt.grpcprotocol.GetUserInfoByTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserByTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER = 0;
  private static final int METHODID_GET_ROLE = 1;
  private static final int METHODID_GET_RESOURCE = 2;
  private static final int METHODID_GET_USER_BY_TOKEN = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER:
          serviceImpl.getUser((com.vnpt.grpcprotocol.UserRequest) request,
              (io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.UserResponse>) responseObserver);
          break;
        case METHODID_GET_ROLE:
          serviceImpl.getRole((com.vnpt.grpcprotocol.RoleRequest) request,
              (io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.RoleResponse>) responseObserver);
          break;
        case METHODID_GET_RESOURCE:
          serviceImpl.getResource((com.vnpt.grpcprotocol.ResourceRequest) request,
              (io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.ResourceResponse>) responseObserver);
          break;
        case METHODID_GET_USER_BY_TOKEN:
          serviceImpl.getUserByToken((com.vnpt.grpcprotocol.GetUserInfoByTokenRequest) request,
              (io.grpc.stub.StreamObserver<com.vnpt.grpcprotocol.GetUserInfoByTokenResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.vnpt.grpcprotocol.UserRequest,
              com.vnpt.grpcprotocol.UserResponse>(
                service, METHODID_GET_USER)))
        .addMethod(
          getGetRoleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.vnpt.grpcprotocol.RoleRequest,
              com.vnpt.grpcprotocol.RoleResponse>(
                service, METHODID_GET_ROLE)))
        .addMethod(
          getGetResourceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.vnpt.grpcprotocol.ResourceRequest,
              com.vnpt.grpcprotocol.ResourceResponse>(
                service, METHODID_GET_RESOURCE)))
        .addMethod(
          getGetUserByTokenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.vnpt.grpcprotocol.GetUserInfoByTokenRequest,
              com.vnpt.grpcprotocol.GetUserInfoByTokenResponse>(
                service, METHODID_GET_USER_BY_TOKEN)))
        .build();
  }

  private static abstract class AuthGrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthGrpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.vnpt.grpcprotocol.AuthGrpcServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthGrpcService");
    }
  }

  private static final class AuthGrpcServiceFileDescriptorSupplier
      extends AuthGrpcServiceBaseDescriptorSupplier {
    AuthGrpcServiceFileDescriptorSupplier() {}
  }

  private static final class AuthGrpcServiceMethodDescriptorSupplier
      extends AuthGrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AuthGrpcServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AuthGrpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthGrpcServiceFileDescriptorSupplier())
              .addMethod(getGetUserMethod())
              .addMethod(getGetRoleMethod())
              .addMethod(getGetResourceMethod())
              .addMethod(getGetUserByTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
