package top.baihu.platform.system.security.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKey;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;

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
