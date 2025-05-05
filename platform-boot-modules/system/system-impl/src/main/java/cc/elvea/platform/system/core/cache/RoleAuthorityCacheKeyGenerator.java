package cc.elvea.platform.system.core.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 */
public class RoleAuthorityCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    @NotNull
    public String getPrefix() {
        return SystemCacheConstants.ROLE_AUTHORITY;
    }

    public CacheKey byRoleId(Long roleId) {
        return this.key(roleId);
    }

    public static CacheKey keyByRoleId(Long roleId) {
        return new RoleAuthorityCacheKeyGenerator().byRoleId(roleId);
    }

}
