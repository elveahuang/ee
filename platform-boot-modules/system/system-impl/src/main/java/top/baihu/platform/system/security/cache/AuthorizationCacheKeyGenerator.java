package top.baihu.platform.system.security.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKey;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;

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

    public CacheKey keyByUuid(String uuid) {
        return this.key("uuid", uuid);
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
