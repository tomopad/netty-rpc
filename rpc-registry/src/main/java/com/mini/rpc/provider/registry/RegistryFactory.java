package com.mini.rpc.provider.registry;

/**
 * @author wdl
 * @date 2021/3/11
 */
public class RegistryFactory {
    private static volatile RegistryService registryService;

    public static RegistryService getInstance(String registryAddr,RegistryType type) throws Exception{
        if (null == registryService) {
            synchronized (RegistryFactory.class){
                if (null == registryService)
                {
                    switch (type){
                        case ZOOKEEPER:
                            registryService = new ZookeeperRegistryService(registryAddr);
                            break;
                        case EUREKA:
                            registryService = new EurekaRegistryService(registryAddr);
                            break;
                        default:
                            throw new IllegalArgumentException("not exist registry service: "+registryAddr);
                    }
                }
            }
        }
        return registryService;
    }
}
