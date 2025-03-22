package cc.elvea.platform.commons.constants;

/**
 * @author elvea
 */
public interface SecurityConstants {

    /**
     * =================================================================================================================
     * JWT Attributes Key
     * =================================================================================================================
     */

    String JWT_KEY_UID = "uid";

    String JWT_KEY_USERNAME = "username";

    String JWT_KEY_AUTHORITIES = "authorities";

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

    String ADMIN_API_REQUEST_PATH = "/api/admin/**";

    String ADMIN_REQUEST_PATH = "/admin/**";

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
