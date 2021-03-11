package com.mini.rpc.handler;

import com.mini.rpc.common.MiniRpcFuture;
import com.mini.rpc.common.MiniRpcRequestHolder;
import com.mini.rpc.common.MiniRpcResponse;
import com.mini.rpc.protocol.MiniRpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wdl
 * @date 2021/3/11
 */
public class RpcResponseHandler extends SimpleChannelInboundHandler<MiniRpcProtocol<MiniRpcResponse>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MiniRpcProtocol<MiniRpcResponse> msg) throws Exception {
        long requestId = msg.getHeader().getRequestId();
        MiniRpcFuture<MiniRpcResponse> future = MiniRpcRequestHolder.REQUEST_MAP.remove(requestId);
        future.getPromise().setSuccess(msg.getBody());
    }
}
