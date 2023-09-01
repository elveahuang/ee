package cn.elvea.platform.commons.core.oapis.dingtalk;

import cn.elvea.platform.commons.core.oapis.dingtalk.cache.Cache;
import cn.elvea.platform.commons.core.oapis.dingtalk.cache.LocalCache;

/**
 * @author elvea
 * @since 0.0.1
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
