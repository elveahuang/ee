package cc.elvea.platform.system.commons.constants;

import static cc.elvea.platform.commons.constants.MappingConstants.API_V1_ADMIN_URL_PREFIX;
import static cc.elvea.platform.commons.constants.MappingConstants.API_V1_URL_PREFIX;

/**
 * @author elvea
 */
public interface SystemMappingConstants {

    // -----------------------------------------------------------------------------------------------------------------
    //  前台接口
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_INITIALIZE = API_V1_URL_PREFIX + "/initialize";
    String API_V1_VERSION = API_V1_URL_PREFIX + "/version";
    String API_V1_HOME = API_V1_URL_PREFIX + "/home";
    String API_V1_CAPTCHA_CODE = API_V1_URL_PREFIX + "/captcha/code";
    String API_V1_CAPTCHA_MAIL = API_V1_URL_PREFIX + "/captcha/mail";
    String API_V1_CAPTCHA_SMS = API_V1_URL_PREFIX + "/captcha/sms";
    String API_V1__USER__ACCOUNT = API_V1_URL_PREFIX + "/user/account";
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
    // 开发测试 & 技术验证
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1__TEST__MESSAGE = API_V1_URL_PREFIX + "/test/message";
    String API_V1__TEST__DATE = API_V1_URL_PREFIX + "/test/date";

    // -----------------------------------------------------------------------------------------------------------------
    // 附件模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1__ATTACHMENT__GET = API_V1_URL_PREFIX + "/attachment";
    String API_V1__ATTACHMENT__TYPE = API_V1_URL_PREFIX + "/attachment/type";
    String API_V1__ATTACHMENT__UPLOAD_FILE = API_V1_URL_PREFIX + "/attachment/upload-file";
    String API_V1__ATTACHMENT__UPLOAD = API_V1_URL_PREFIX + "/attachment/upload";
    String API_V1__ATTACHMENT__EDITOR_UPLOAD = API_V1_URL_PREFIX + "/attachment/editor/upload";

    // -----------------------------------------------------------------------------------------------------------------
    // 账号模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1__ACCOUNT__INFO = API_V1_URL_PREFIX + "/account";
    String API_V1__ACCOUNT__REGISTER = API_V1_URL_PREFIX + "/account/register";
    String API_V1__ACCOUNT__LOGOUT = API_V1_URL_PREFIX + "/account/logout";
    String API_V1__ACCOUNT__CHECK_USERNAME = API_V1_URL_PREFIX + "/account/check-username";
    String API_V1__ACCOUNT__CHECK_EMAIL = API_V1_URL_PREFIX + "/account/check-email";
    String API_V1__ACCOUNT__CHECK_MOBILE = API_V1_URL_PREFIX + "/account/check-mobile";
    String API_V1__ACCOUNT__FORGOT_PASSWORD = API_V1_URL_PREFIX + "/account/forgot-password";
    String API_V1__ACCOUNT__RESET_PASSWORD = API_V1_URL_PREFIX + "/account/reset-password";
    String API_V1__ACCOUNT__CHANGE_PASSWORD = API_V1_URL_PREFIX + "/account/change-password";
    String API_V1__ACCOUNT__UPDATE = API_V1_URL_PREFIX + "/account/update-account";

    // -----------------------------------------------------------------------------------------------------------------
    // 用户模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__USER__LIST = API_V1_ADMIN_URL_PREFIX + "/user/list";
    String API_V1_ADMIN__USER__DETAILS = API_V1_ADMIN_URL_PREFIX + "/user/details";
    String API_V1_ADMIN__USER__PREPARE = API_V1_ADMIN_URL_PREFIX + "/user/prepare";
    String API_V1_ADMIN__USER__SAVE = API_V1_ADMIN_URL_PREFIX + "/user/save";
    String API_V1_ADMIN__USER__DELETE = API_V1_ADMIN_URL_PREFIX + "/user/delete";

    String API_V1__USER__INFO = API_V1_URL_PREFIX + "/user";
    String API_V1__USER__REGISTER = API_V1_URL_PREFIX + "/register";
    String API_V1__USER__LOGOUT = API_V1_URL_PREFIX + "/logout";
    String API_V1__USER__CHECK_USERNAME = API_V1_URL_PREFIX + "/user/check-username";
    String API_V1__USER__CHECK_EMAIL = API_V1_URL_PREFIX + "/user/check-email";
    String API_V1__USER__CHECK_MOBILE = API_V1_URL_PREFIX + "/user/check-mobile";
    String API_V1__USER__FORGOT_PASSWORD = API_V1_URL_PREFIX + "/forgot-password";
    String API_V1__USER__RESET_PASSWORD = API_V1_URL_PREFIX + "/reset-password";
    String API_V1__USER__CHANGE_PASSWORD = API_V1_URL_PREFIX + "/change-password";

    // -----------------------------------------------------------------------------------------------------------------
    // 会话记录模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__USER_SESSION__LIST = API_V1_ADMIN_URL_PREFIX + "/user-session/list";

    // -----------------------------------------------------------------------------------------------------------------
    // 认证授权模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__CLIENT__LIST = API_V1_ADMIN_URL_PREFIX + "/client/list";
    String API_V1_ADMIN__AUTHORIZATION__LIST = API_V1_ADMIN_URL_PREFIX + "/authorization/list";
    String API_V1_ADMIN__AUTHORIZATION_CONSENT__LIST = API_V1_ADMIN_URL_PREFIX + "/authorization-consent/list";

    // -----------------------------------------------------------------------------------------------------------------
    // 公告模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__ANNOUNCEMENT__LIST = API_V1_ADMIN_URL_PREFIX + "/announcement/list";
    String API_V1_ADMIN__ANNOUNCEMENT__DETAILS = API_V1_ADMIN_URL_PREFIX + "/announcement/details";
    String API_V1_ADMIN__ANNOUNCEMENT__PREPARE = API_V1_ADMIN_URL_PREFIX + "/announcement/prepare";
    String API_V1_ADMIN__ANNOUNCEMENT__SAVE = API_V1_ADMIN_URL_PREFIX + "/announcement/save";
    String API_V1_ADMIN__ANNOUNCEMENT__DELETE = API_V1_ADMIN_URL_PREFIX + "/announcement/delete";

    String API_V1__ANNOUNCEMENT__LIST = API_V1_URL_PREFIX + "/announcement/list";
    String API_V1__ANNOUNCEMENT__DETAILS = API_V1_URL_PREFIX + "/announcement/details";

    // -----------------------------------------------------------------------------------------------------------------
    // 国际化模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__LABEL__TRANSLATE = API_V1_ADMIN_URL_PREFIX + "/label/translate";
    String API_V1_ADMIN__LABEL__GENERATE = API_V1_ADMIN_URL_PREFIX + "/label/generate";

    // -----------------------------------------------------------------------------------------------------------------
    // 消息模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1__NOTICE__LIST = API_V1_URL_PREFIX + "/notice/list";
    String API_V1__NOTICE__DETAILS = API_V1_URL_PREFIX + "/notice/details";

    // -----------------------------------------------------------------------------------------------------------------
    // 标签模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__TAG__TYPE_LIST = API_V1_ADMIN_URL_PREFIX + "/tag/type/list";
    String API_V1_ADMIN__TAG__LIST = API_V1_ADMIN_URL_PREFIX + "/tag/list";
    String API_V1_ADMIN__TAG__DETAILS = API_V1_ADMIN_URL_PREFIX + "/tag/details";
    String API_V1_ADMIN__TAG__SAVE = API_V1_ADMIN_URL_PREFIX + "/tag/save";
    String API_V1_ADMIN__TAG__DELETE = API_V1_ADMIN_URL_PREFIX + "/tag/delete";

    String API_V1__TAG__TYPE = API_V1_URL_PREFIX + "/tag/type";
    String API_V1__TAG__SEARCH = API_V1_URL_PREFIX + "/tag/search";

    // -----------------------------------------------------------------------------------------------------------------
    // 字典模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__DICT__TYPE_LIST = API_V1_ADMIN_URL_PREFIX + "/dict/type/list";
    String API_V1_ADMIN__DICT__LIST = API_V1_ADMIN_URL_PREFIX + "/dict/list";
    String API_V1_ADMIN__DICT__DETAILS = API_V1_ADMIN_URL_PREFIX + "/dict/details";
    String API_V1_ADMIN__DICT__SAVE = API_V1_ADMIN_URL_PREFIX + "/dict/save";
    String API_V1_ADMIN__DICT__DELETE = API_V1_ADMIN_URL_PREFIX + "/dict/delete";

    String API_V1__DICT__TYPE = API_V1_URL_PREFIX + "/dict/type";
    String API_V1__DICT__SEARCH = API_V1_URL_PREFIX + "/dict/search";

    // -----------------------------------------------------------------------------------------------------------------
    // 宣传栏模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__BANNER__LIST = API_V1_ADMIN_URL_PREFIX + "/banner/list";
    String API_V1_ADMIN__BANNER__DETAILS = API_V1_ADMIN_URL_PREFIX + "/banner/details";
    String API_V1_ADMIN__BANNER__DELETE = API_V1_ADMIN_URL_PREFIX + "/banner/delete";
    String API_V1_ADMIN__BANNER__SAVE = API_V1_ADMIN_URL_PREFIX + "/banner/save";

    String API_V1__BANNER__LIST = API_V1_URL_PREFIX + "/banner/list";

    // -----------------------------------------------------------------------------------------------------------------
    // 商城模块
    // -----------------------------------------------------------------------------------------------------------------

    String API_V1_ADMIN__ORDER__LIST = API_V1_ADMIN_URL_PREFIX + "/order/list";
    String API_V1_ADMIN__ORDER__DETAILS = API_V1_ADMIN_URL_PREFIX + "/order/details";
    String API_V1_ADMIN__ORDER__DELETE = API_V1_ADMIN_URL_PREFIX + "/order/delete";
    String API_V1_ADMIN__ORDER__SAVE = API_V1_ADMIN_URL_PREFIX + "/order/save";
    String API_V1_ADMIN__VIP__LIST = API_V1_ADMIN_URL_PREFIX + "/vip/list";

    String API_V1__ORDER__LIST = API_V1_URL_PREFIX + "/order/list";
    String API_V1__ORDER__CONFIRM = API_V1_URL_PREFIX + "/order/confirm";
    String API_V1__ORDER__SUBMIT = API_V1_URL_PREFIX + "/order/submit";
    String API_V1__ORDER__CANCEL = API_V1_URL_PREFIX + "/order/cancel";
    String API_V1__ORDER__DETAILS = API_V1_URL_PREFIX + "/order/details";
    String API_V1__ORDER__DELETE = API_V1_URL_PREFIX + "/order/delete";

    String API_V1__PAY__TYPE = API_V1_URL_PREFIX + "/pay/type";

    String API_V1__VIP__TYPE = API_V1_URL_PREFIX + "/vip/type";
    String API_V1__VIP__ORDER__CONFIRM = API_V1_URL_PREFIX + "/vip/order/confirm";

}
