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
