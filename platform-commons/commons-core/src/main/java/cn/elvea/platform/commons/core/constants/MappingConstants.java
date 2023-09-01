package cn.elvea.platform.commons.core.constants;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MappingConstants {

    /**
     * 请求路径前缀
     */
    String URL_PREFIX = "/";

    /**
     * 接口请求路径前缀
     */
    String API_URL_PREFIX = URL_PREFIX + "api";

    /**
     * 接口请求路径前缀
     * Version - 1
     */
    String API_V1_URL_PREFIX = API_URL_PREFIX + "/v1";

    /**
     * 接口请求路径前缀
     * Version - 2
     */
    String API_V2_URL_PREFIX = API_URL_PREFIX + "/v2";

    /**
     * 后台管理系统请求路径前缀
     * Version - 1
     */
    String API_V1_ADMIN_URL_PREFIX = API_V1_URL_PREFIX + "/admin";

    /**
     * 后台管理系统请求路径前缀
     * Version - 2
     */
    String API_V2_ADMIN_URL_PREFIX = API_V2_URL_PREFIX + "/admin";

    /**
     * 微服务内部调用请求路径前缀
     * Version - 1
     */
    String API_V1_FEIGN_URL_PREFIX = API_V1_URL_PREFIX + "/feign";

    /**
     * 微服务内部调用请求路径前缀
     * Version - 2
     */
    String API_V2_FEIGN_URL_PREFIX = API_V1_URL_PREFIX + "/feign";

}
