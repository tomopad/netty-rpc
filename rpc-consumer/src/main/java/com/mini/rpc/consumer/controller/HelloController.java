package com.mini.rpc.consumer.controller;

import com.mimi.rpc.provider.facade.HelloFacade;
import com.mini.rpc.consumer.annotation.RpcReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection","SpringJavaInjectionPointsAutowiringInspection"})
    @RpcReference(serviceVersion = "1.0.0",timeout = 3000)
    private HelloFacade helloFacade;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        return helloFacade.hello("mini rpc");
    }
}
