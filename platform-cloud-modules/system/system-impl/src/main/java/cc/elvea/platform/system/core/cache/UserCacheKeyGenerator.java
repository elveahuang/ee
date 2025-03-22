package cc.elvea.platform.system.core.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

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
