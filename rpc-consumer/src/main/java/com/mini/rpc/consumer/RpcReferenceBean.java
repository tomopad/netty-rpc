package com.mini.rpc.consumer;

import com.mini.rpc.provider.registry.RegistryFactory;
import com.mini.rpc.provider.registry.RegistryService;
import com.mini.rpc.provider.registry.RegistryType;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class RpcReferenceBean implements FactoryBean<Object> {
    @Setter
    private Class<?> interfaceClass;
    @Setter
    private String serviceVersion;
    @Setter
    private String registryType;
    @Setter
    private String registryAddr;
    @Setter
    private long timeout;

    private Object object;

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    public void init() throws Exception{
        RegistryService registryService = RegistryFactory.getInstance(this.registryAddr, RegistryType.valueOf(registryType));
        this.object = Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[]{interfaceClass},new RpcInvokerProxy(serviceVersion, timeout, registryService));
    }
}
