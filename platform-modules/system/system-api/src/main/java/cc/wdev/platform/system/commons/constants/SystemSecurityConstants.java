package cc.wdev.platform.system.commons.constants;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1_PREFIX;

public interface SystemSecurityConstants {
    /**
     * =================================================================================================================
     * 忽略权限检查的地址
     * =================================================================================================================
     */

    String[] WEB_EXCLUDE_URLS = {
    };

    String[] API_EXCLUDE_URLS = {
        API_V1_PREFIX + "/initialize",
        API_V1_PREFIX + "/agreement",
        API_V1_PREFIX + "/privacy",
        API_V1_PREFIX + "/captcha/**",
        API_V1_PREFIX + "/oapis/wechat/mp/signature",
        API_V1_PREFIX + "/oapis/wechat/mp/callback",
        API_V1_PREFIX + "/captcha/sms/check",
        API_V1_PREFIX + "/captcha/sms",
        API_V1_PREFIX + "/captcha/mail/check",
        API_V1_PREFIX + "/captcha/mail",
        API_V1_PREFIX + "/captcha/code/check",
        API_V1_PREFIX + "/captcha/code",
        API_V1_PREFIX + "/captcha/code",
    };
}
