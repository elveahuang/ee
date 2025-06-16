package cc.elvea.platform.commons.oapis.dingtalk;

import cc.elvea.platform.commons.oapis.dingtalk.cache.LocalCache;
import cc.elvea.platform.commons.oapis.dingtalk.token.TokenManager;

/**
 * @author elvea
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
