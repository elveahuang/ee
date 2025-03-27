package cc.elvea.platform.system.core.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 */
public class AccountCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    @NotNull
    public String getPrefix() {
        return SystemCacheConstants.ACCOUNT;
    }

    public CacheKey cacheKeyByUsername(String username) {
        return this.key("username", username);
    }

    public CacheKey cacheKeyByEmail(String email) {
        return this.key("email", email);
    }

    public CacheKey cacheKeyByMobile(String mobileCountryCode, String mobileNumber) {
        return this.key("mobile", mobileCountryCode, mobileNumber);
    }

}
