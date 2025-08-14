package top.baihu.platform.system.core.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKey;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;

/**
 * @author elvea
 */
public class UserCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.USER;
    }

    public CacheKey keyByUsername(String username) {
        return this.key("username", username);
    }

    public CacheKey keyByEmail(String email) {
        return this.key("email", email);
    }

    public CacheKey keyByMobile(String mobileCountryCode, String mobile) {
        return this.key("mobile", mobileCountryCode, mobile);
    }

}
