package cc.wdev.platform.commons.constants;

/**
 * @author elvea
 */
public interface MappingConstants {

    /**
     * 请求前缀
     */
    String URL_PREFIX = "";

    /**
     * 接口前缀
     */
    String API_PREFIX = URL_PREFIX + "/api";

    /**
     * 接口前缀
     * Version - 1
     */
    String API_V1_PREFIX = API_PREFIX + "/v1";

    /**
     * 后端接口前缀
     */
    String API_V1_ADMIN_PREFIX = API_V1_PREFIX + "/admin";

    /**
     * 前端接口前缀
     */
    String API_V1_APP_PREFIX = API_V1_PREFIX + "/";

    /**
     * 微服务前缀
     */
    String FEIGN_PREFIX = URL_PREFIX + "/feign";

}
