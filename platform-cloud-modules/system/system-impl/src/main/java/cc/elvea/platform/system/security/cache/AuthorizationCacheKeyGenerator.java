package cc.elvea.platform.system.security.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 */
public class AuthorizationCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.AUTHORIZATION;
    }

    public CacheKey keyById(Long id) {
        return this.key(id);
    }

    public CacheKey keyByState(String state) {
        return this.key("state", state);
    }

    public CacheKey keyByAuthorizationCodeValue(String authorizationCodeValue) {
        return this.key("authorization_code", authorizationCodeValue);
    }

    public CacheKey keyByOidcIdTokenValue(String oidcIdTokenValue) {
        return this.key("oidc_id_token", oidcIdTokenValue);
    }

    public CacheKey keyByAccessTokenValue(String accessTokenValue) {
        return this.key("access_token", accessTokenValue);
    }

    public CacheKey keyByRefreshTokenValue(String refreshTokenValue) {
        return this.key("refresh_token", refreshTokenValue);
    }

}
