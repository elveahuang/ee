package cn.elvea.platform.system.security.cache;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 * @since 0.0.1
 */
public class AuthorizationConsentCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.AUTHORIZATION;
    }

    public CacheKey byKey(String clientId, String principalName) {
        return this.key(clientId, principalName);
    }

}
