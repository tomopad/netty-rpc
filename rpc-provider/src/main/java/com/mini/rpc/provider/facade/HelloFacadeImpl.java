package com.mini.rpc.provider.facade;

import com.mimi.rpc.provider.facade.HelloFacade;
import com.mini.rpc.provider.annotation.RpcService;

/**
 * @author wdl
 * @date 2021/3/11
 */
@RpcService(serviceInterface = HelloFacade.class,serviceVersion = "1.0.0")
public class HelloFacadeImpl implements HelloFacade {

    @Override
    public String hello(String name) {
        return "hello "+name;
    }
}
