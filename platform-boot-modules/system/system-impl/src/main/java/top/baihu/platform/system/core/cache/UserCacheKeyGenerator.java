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

    public CacheKey byUsername(String username) {
        return this.key("username", username);
    }

    public CacheKey byEmail(String email) {
        return this.key("email", email);
    }

    public CacheKey byMobile(String mobileCountryCode, String mobileNumber) {
        return this.key("mobile", mobileCountryCode, mobileNumber);
    }

}
