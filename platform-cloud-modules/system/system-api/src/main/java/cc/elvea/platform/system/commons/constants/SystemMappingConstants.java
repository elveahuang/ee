package cc.elvea.platform.system.commons.constants;

/**
 * @author elvea
 */
public interface SystemMappingConstants {

    // -----------------------------------------------------------------------------------------------------------------
    // 接口前缀
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 接口请求路径
     */
    String API_URL_PREFIX = "/api";

    /**
     * 接口请求路径
     * Version 1
     */
    String API_V1_URL_PREFIX = API_URL_PREFIX + "/v1";

    /**
     * 前台门户系统请求路径前缀
     */
    String API_V1_HOME_URL_PREFIX = API_V1_URL_PREFIX + "/home";

    /**
     * 后台管理系统请求路径前缀
     */
    String API_V1_ADMIN_URL_PREFIX = API_V1_URL_PREFIX + "/admin";

    /**
     * 微服务内部调用请求路径前缀
     */
    String API_V1_FEIGN_URL_PREFIX = API_V1_URL_PREFIX + "/feign";

    // -----------------------------------------------------------------------------------------------------------------
    // 通用接口
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_INITIALIZE = API_V1_URL_PREFIX + "/initialize";

    String API_V1_VERSION = API_V1_URL_PREFIX + "/version";

    String API_V1__USER__INFO = API_V1_URL_PREFIX + "/user";

    String API_V1__USER__REGISTER = API_V1_URL_PREFIX + "/register";

    String API_V1__USER__FORGOT_PASSWORD = API_V1_URL_PREFIX + "/forgot-password";

    String API_V1__LINK__GENERATE = API_V1_URL_PREFIX + "/link/generate";

    String API_V1__LINK__GO = API_V1_URL_PREFIX + "/link/go";

    String API_V1__LARK__SIGNATURE = API_V1_URL_PREFIX + "/lark/signature";

    String API_V1__LARK__CALLBACK = API_V1_URL_PREFIX + "/lark/callback";

    String API_V1__DINGTALK__SIGNATURE = API_V1_URL_PREFIX + "/dingtalk/signature";

    String API_V1__DINGTALK__CALLBACK = API_V1_URL_PREFIX + "/dingtalk/callback";

    String API_V1__WECHAT__SIGNATURE = API_V1_URL_PREFIX + "/wechat/signature";

    String API_V1__WECHAT__CALLBACK = API_V1_URL_PREFIX + "/wechat/callback";

    String API_V1__WECOM__SIGNATURE = API_V1_URL_PREFIX + "/wecom/signature";

    String API_V1__WECOM__CALLBACK = API_V1_URL_PREFIX + "/wecom/callback";

    // -----------------------------------------------------------------------------------------------------------------
    // 前台
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_HOME__USER__LIST = API_V1_HOME_URL_PREFIX + "/user/list";

    // -----------------------------------------------------------------------------------------------------------------
    // 后台
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__USER__LIST = API_V1_ADMIN_URL_PREFIX + "/user/list";

    String API_V1_ADMIN__CLIENT__LIST = API_V1_ADMIN_URL_PREFIX + "/client/list";

    String API_V1_ADMIN__AUTHORIZATION__LIST = API_V1_ADMIN_URL_PREFIX + "/authorization/list";

    String API_V1_ADMIN__AUTHORIZATION_CONSENT__LIST = API_V1_ADMIN_URL_PREFIX + "/authorization-consent/list";

    // -----------------------------------------------------------------------------------------------------------------
    // 服务
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_FEIGN__USER__FIND_BY_USERNAME = API_V1_FEIGN_URL_PREFIX + "/user/find-by-username";

    String API_V1_FEIGN__USER_SESSION__FIND_BY_USERNAME = API_V1_FEIGN_URL_PREFIX + "/user-session/save-user-session";

    String API_V1_FEIGN__SECURITY__CLIENT__SAVE = API_V1_FEIGN_URL_PREFIX + "/security/client/save";

    String API_V1_FEIGN__SECURITY__CLIENT__FIND_BY_ID = API_V1_FEIGN_URL_PREFIX + "/security/client/find-by-id";

    String API_V1_FEIGN__SECURITY__CLIENT__FIND_BY_CLIENT_ID = API_V1_FEIGN_URL_PREFIX + "/security/client/find-by-client-id";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION__SAVE = API_V1_FEIGN_URL_PREFIX + "/security/authorization/save";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION__DELETE_BY_ID = API_V1_FEIGN_URL_PREFIX + "/security/authorization/delete-by-id";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_ID = API_V1_FEIGN_URL_PREFIX + "/security/authorization/find-by-id";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_STATE = API_V1_FEIGN_URL_PREFIX + "/security/authorization/find-by-state";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_AUTHORIZATION_CODE_VALUE = API_V1_FEIGN_URL_PREFIX + "/security/authorization/find-by-authorization-code-value";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_OIDC_ID_TOKEN_VALUE = API_V1_FEIGN_URL_PREFIX + "/security/authorization/find-by-oidc-id-token-value";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_ACCESS_TOKEN_VALUE = API_V1_FEIGN_URL_PREFIX + "/security/authorization/find-by-access-token-value";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_REFRESH_TOKEN_VALUE = API_V1_FEIGN_URL_PREFIX + "/security/authorization/find-by-refresh-token-value";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION_CONSENT__SAVE = API_V1_FEIGN_URL_PREFIX + "/security/authorization-consent/save";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION_CONSENT__FIND_BY_KEY = API_V1_FEIGN_URL_PREFIX + "/security/authorization-consent/find-by-key";

    String API_V1_FEIGN__SECURITY__AUTHORIZATION_CONSENT__DELETE_BY_KEY = API_V1_FEIGN_URL_PREFIX + "/security/authorization-consent/delete-by-key";

}
