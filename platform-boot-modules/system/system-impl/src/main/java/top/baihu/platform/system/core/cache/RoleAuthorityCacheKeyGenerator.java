package top.baihu.platform.system.core.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKey;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;

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
