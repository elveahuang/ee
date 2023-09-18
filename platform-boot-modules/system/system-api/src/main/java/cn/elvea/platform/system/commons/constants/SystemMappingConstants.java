package cn.elvea.platform.system.commons.constants;

import cn.elvea.platform.commons.core.constants.MappingConstants;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface SystemMappingConstants {

    // -----------------------------------------------------------------------------------------------------------------
    //  前台接口
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_INITIALIZE = MappingConstants.API_V1_URL_PREFIX + "/initialize";

    String API_V1_HOME = MappingConstants.API_V1_URL_PREFIX + "/home";

    String API_V1_CAPTCHA_CODE = MappingConstants.API_V1_URL_PREFIX + "/captcha/code";

    String API_V1_CAPTCHA_MAIL = MappingConstants.API_V1_URL_PREFIX + "/captcha/mail";

    String API_V1_CAPTCHA_SMS = MappingConstants.API_V1_URL_PREFIX + "/captcha/sms";

    String API_V1__USER__INFO = MappingConstants.API_V1_URL_PREFIX + "/user";

    String API_V1__USER__REGISTER = MappingConstants.API_V1_URL_PREFIX + "/register";

    String API_V1__USER__FORGOT_PASSWORD = MappingConstants.API_V1_URL_PREFIX + "/forgot-password";

    String API_V1__LINK__GENERATE = MappingConstants.API_V1_URL_PREFIX + "/link/generate";

    String API_V1__LINK__GO = MappingConstants.API_V1_URL_PREFIX + "/link/go";

    String API_V1__LARK__SIGNATURE = MappingConstants.API_V1_URL_PREFIX + "/lark/signature";

    String API_V1__LARK__CALLBACK = MappingConstants.API_V1_URL_PREFIX + "/lark/callback";

    String API_V1__DINGTALK__SIGNATURE = MappingConstants.API_V1_URL_PREFIX + "/dingtalk/signature";

    String API_V1__DINGTALK__CALLBACK = MappingConstants.API_V1_URL_PREFIX + "/dingtalk/callback";

    String API_V1__WECHAT__SIGNATURE = MappingConstants.API_V1_URL_PREFIX + "/wechat/signature";

    String API_V1__WECHAT__CALLBACK = MappingConstants.API_V1_URL_PREFIX + "/wechat/callback";

    String API_V1__WECOM__SIGNATURE = MappingConstants.API_V1_URL_PREFIX + "/wecom/signature";

    String API_V1__WECOM__CALLBACK = MappingConstants.API_V1_URL_PREFIX + "/wecom/callback";

    // -----------------------------------------------------------------------------------------------------------------
    // 后台管理
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__USER__LIST = MappingConstants.API_V1_ADMIN_URL_PREFIX + "/user/list";

    String API_V1_ADMIN__USER_SESSION__LIST = MappingConstants.API_V1_ADMIN_URL_PREFIX + "/user-session/list";

    String API_V1_ADMIN__CLIENT__LIST = MappingConstants.API_V1_ADMIN_URL_PREFIX + "/client/list";

    String API_V1_ADMIN__AUTHORIZATION__LIST = MappingConstants.API_V1_ADMIN_URL_PREFIX + "/authorization/list";

    String API_V1_ADMIN__AUTHORIZATION_CONSENT__LIST = MappingConstants.API_V1_ADMIN_URL_PREFIX + "/authorization-consent/list";

    String API_V1_ADMIN__LABEL__TRANSLATE = MappingConstants.API_V1_ADMIN_URL_PREFIX + "/label/translate";

    String API_V1_ADMIN__LABEL__GENERATE = MappingConstants.API_V1_ADMIN_URL_PREFIX + "/label/generate";

    // -----------------------------------------------------------------------------------------------------------------
    // 公告资讯
    // -----------------------------------------------------------------------------------------------------------------

    // 获取公告资讯列表
    String API_V1__ANNOUNCEMENT__LIST = MappingConstants.API_V1_URL_PREFIX + "/announcement/list";

    // 获取公告资讯详情
    String API_V1__ANNOUNCEMENT__DETAILS = MappingConstants.API_V1_URL_PREFIX + "/announcement/details";

}
