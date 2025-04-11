package cc.elvea.platform.commons.oapis.lark;

import cc.elvea.platform.commons.oapis.lark.cache.Cache;
import cc.elvea.platform.commons.oapis.lark.cache.LocalCache;

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
