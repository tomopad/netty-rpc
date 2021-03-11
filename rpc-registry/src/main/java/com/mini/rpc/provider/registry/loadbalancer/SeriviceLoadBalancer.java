package com.mini.rpc.provider.registry.loadbalancer;

import java.util.List;

/**
 * @author wdl
 * @date 2021/3/11
 */
public interface SeriviceLoadBalancer<T> {
    T select(List<T> servers, int hashCode);
}
