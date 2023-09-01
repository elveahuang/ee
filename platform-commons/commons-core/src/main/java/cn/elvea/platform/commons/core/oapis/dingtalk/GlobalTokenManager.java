package cn.elvea.platform.commons.core.oapis.dingtalk;

import cn.elvea.platform.commons.core.oapis.dingtalk.cache.LocalCache;
import cn.elvea.platform.commons.core.oapis.dingtalk.token.TokenManager;

/**
 * @author elvea
 * @since 0.0.1
 */
public class GlobalTokenManager {

    private static volatile TokenManager globalTokenManager = new TokenManager(LocalCache.getInstance());

    public static TokenManager getTokenManager() {
        return globalTokenManager;
    }

    public static void setTokenManager(TokenManager tokenManager) {
        globalTokenManager = tokenManager;
    }

}
