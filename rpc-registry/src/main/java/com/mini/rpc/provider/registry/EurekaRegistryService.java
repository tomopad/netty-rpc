package com.mini.rpc.provider.registry;

import com.mini.rpc.common.ServiceMeta;

/**
 * @author wdl
 * @date 2021/3/11
 */
public class EurekaRegistryService implements RegistryService{

    public EurekaRegistryService(String registryAddr) {
        // TODO
    }

    @Override
    public void register(ServiceMeta serviceMeta) throws Exception {
        // TODO
    }

    @Override
    public void unRegister(ServiceMeta serviceMeta) throws Exception {
        // TODO
    }

    @Override
    public ServiceMeta discovery(String serviceName, int invokerHashCode) throws Exception {
        // TODO
        return null;
    }

    @Override
    public void destroy() throws Exception {
        // TODO
    }
}
