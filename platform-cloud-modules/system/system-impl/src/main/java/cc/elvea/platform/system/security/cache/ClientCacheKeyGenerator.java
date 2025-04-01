package cc.elvea.platform.system.security.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 */
public class ClientCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.CLIENT;
    }

    public CacheKey keyById(Long id) {
        return this.key(id);
    }

    public CacheKey keyByClientId(String clientId) {
        return this.key("client_id", clientId);
    }

}
