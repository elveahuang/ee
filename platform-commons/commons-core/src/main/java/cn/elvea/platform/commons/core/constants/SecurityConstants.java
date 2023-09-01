package cn.elvea.platform.commons.core.constants;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface SecurityConstants {

    /**
     * =================================================================================================================
     * JWT Attributes Key
     * =================================================================================================================
     */

    String JWT_KEY_UID = "uid";

    String JWT_KEY_SID = "sid";

    String JWT_KEY_USERNAME = "username";

    String JWT_KEY_NAME = "name";

    String JWT_KEY_AUTHORITIES = "authorities";

    String JWT_KEY_ACCOUNT = "account";

    String JWT_KEY_TOKEN_TYPE = "type";

    String JWT_TOKEN_TYPE_ACCESS_TOKEN = "access_token";

    String JWT_TOKEN_TYPE_REFRESH_TOKEN = "refresh_token";

    /**
     * =================================================================================================================
     * JWT Auth Endpoint
     * =================================================================================================================
     */

    String JWT_AUTH_TOKEN_ENDPOINT = "/api/auth/token";

    /**
     * =================================================================================================================
     * OAuth2 Endpoint
     * =================================================================================================================
     */

    String OAUTH_AUTHORIZATION_ENDPOINT = "/oauth/authorize";

    String OAUTH_DEVICE_AUTHORIZATION_ENDPOINT = "/oauth/device_authorization";

    String OAUTH_DEVICE_VERIFICATION_ENDPOINT = "/oauth/device_verification";

    String OAUTH_TOKEN_ENDPOINT = "/oauth/token";

    String OAUTH_TOKEN_INTROSPECTION_ENDPOINT = "/oauth/introspect";

    String OAUTH_TOKEN_REVOCATION_ENDPOINT = "/oauth/revoke";

    String OAUTH_JWK_SET_ENDPOINT = "/oauth/jwks";

    String OAUTH_OIDC_USER_INFO_ENDPOINT = "/userinfo";

    String OAUTH_OIDC_LOGOUT_ENDPOINT = "/connect/logout";

    String OAUTH_OIDC_CLIENT_REGISTRATION_ENDPOINT = "/connect/register";

    /**
     * =====================¬ø============================================================================================
     * 基础链接
     * =================================================================================================================
     */

    String API_REQUEST_PATH = "/api/**";

    /**
     * =================================================================================================================
     * 忽略权限检查的地址
     * =================================================================================================================
     */

    String[] API_EXCLUDE_URLS = {
    };

    String[] WEB_EXCLUDE_URLS = {
            "/",
            "/favicon.ico",
            "/assets/**",
            "/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger.html",
            "/webjars/**",
            "/public/**",
            "/static/**",
            "/actuator/**",
    };

}
