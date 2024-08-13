package org.example.order_service.gRPC;

import io.grpc.*;

public class GrpcClientInterceptor implements ClientInterceptor {

    private static final ThreadLocal<Metadata> metadataThreadLocal = new ThreadLocal<>();

    public static void setMetadata(Metadata metadata) {
        metadataThreadLocal.set(metadata);
    }

    public static void clearMetadata() {
        metadataThreadLocal.remove();
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {

        return new ForwardingClientCall.SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                Metadata metadata = metadataThreadLocal.get();
                if (metadata != null) {
                    headers.merge(metadata);
                }
                super.start(responseListener, headers);
            }
        };
    }
}
