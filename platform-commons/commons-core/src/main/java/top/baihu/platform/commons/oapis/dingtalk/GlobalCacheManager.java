package top.baihu.platform.commons.oapis.dingtalk;

import top.baihu.platform.commons.oapis.dingtalk.cache.Cache;
import top.baihu.platform.commons.oapis.dingtalk.cache.LocalCache;

/**
 * @author elvea
 */
public class GlobalCacheManager {

    private static volatile Cache globalCache = LocalCache.getInstance();

    public static Cache getCache() {
        return globalCache;
    }

    public static void setCache(Cache cache) {
        globalCache = cache;
    }

}
