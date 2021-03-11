package com.mini.rpc.provider.registry;

import com.mini.rpc.common.ServiceMeta;

/**
 * @author wdl
 * @date 2021/3/11
 */
public interface RegistryService {

    /**
     * 服务注册
     * @param serviceMeta 服务信息元数据
     * @throws Exception
     */
    void register(ServiceMeta serviceMeta) throws Exception;

    /**
     * 服务注销
     * @param serviceMeta 服务信息元数据
     * @throws Exception
     */
    void unRegister(ServiceMeta serviceMeta) throws Exception;

    /**
     * 服务发现
     * @param serviceName 服务名称
     * @param invokerHashCode
     * @return 服务信息元数据
     * @throws Exception
     */
    ServiceMeta discovery(String serviceName,int invokerHashCode) throws Exception;

    /**
     * 注册中心销毁
     * @throws Exception
     */
    void destroy() throws Exception;
}
