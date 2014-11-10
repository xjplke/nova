package me.itsoho.shiro.cluster.cache;

import org.apache.shiro.cache.Cache;

/**
 * custom shiro cache manager interface
 *
 * @author michael
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}
