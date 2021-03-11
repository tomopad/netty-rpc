package com.mini.rpc.handler;

import com.mini.rpc.common.MiniRpcRequest;
import com.mini.rpc.common.MiniRpcResponse;
import com.mini.rpc.common.RpcServiceHelper;
import com.mini.rpc.protocol.MiniRpcProtocol;
import com.mini.rpc.protocol.MsgHeader;
import com.mini.rpc.protocol.MsgStatus;
import com.mini.rpc.protocol.MsgType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.reflect.FastClass;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author wdl
 * @date 2021/3/11
 */
@Slf4j
public class RpcRequestHandler extends SimpleChannelInboundHandler<MiniRpcProtocol<MiniRpcRequest>> {

    private final Map<String,Object> rpcServiceMap;

    public RpcRequestHandler(Map<String,Object> rpcServiceMap){
        this.rpcServiceMap = rpcServiceMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MiniRpcProtocol<MiniRpcRequest> protocol) throws Exception {
        RpcRequestProcessor.submitRequest(()->{
            MiniRpcProtocol<MiniRpcResponse> resProtocol = new MiniRpcProtocol<>();
            MiniRpcResponse response = new MiniRpcResponse();
            MsgHeader header = protocol.getHeader();
            header.setMsgType((byte) MsgType.RESPONSE.getType());
            try {
                Object result = handler(protocol.getBody());
                response.setData(result);

                header.setStatus((byte) MsgStatus.SUCCESS.getCode());
                resProtocol.setHeader(header);
                resProtocol.setBody(response);
            } catch (InvocationTargetException e) {
                header.setStatus((byte) MsgStatus.FAIL.getCode());
                response.setMessage(e.toString());
                log.error("process request {} error",header.getRequestId(),e);
            }
            ctx.writeAndFlush(resProtocol);
        });
    }

    private Object handler(MiniRpcRequest request) throws InvocationTargetException {
        String serviceKey = RpcServiceHelper.buildServiceKey(request.getClassName(),request.getServiceVersion());
        Object serviceBean = rpcServiceMap.get(serviceKey);
        if (serviceBean == null){
            throw new RuntimeException(String.format("service not exist: %s:%s",request.getClassName(),request.getMethodName()));
        }

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParams();

        FastClass fastClass = FastClass.create(serviceClass);
        int methodIndex = fastClass.getIndex(methodName,parameterTypes);
        return fastClass.invoke(methodIndex,serviceBean,parameters);
    }


}
