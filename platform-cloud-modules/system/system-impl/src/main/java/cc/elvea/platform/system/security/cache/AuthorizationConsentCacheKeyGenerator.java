package cc.elvea.platform.system.security.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
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
