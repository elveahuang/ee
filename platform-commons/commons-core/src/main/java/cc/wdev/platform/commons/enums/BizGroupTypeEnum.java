package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 基础核心业务分组类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum BizGroupTypeEnum implements BaseBizGroupTypeEnum {
    RESPONSE_CODE("RESPONSE_CODE", "响应码"),
    LANG_TYPE("LANG_TYPE", "语言类型"),
    CONFIG_TYPE("CONFIG_TYPE", "自动配置方式"),
    ACTION_TYPE("ACTION_TYPE", "操作类型"),
    STORAGE_TYPE("STORAGE_TYPE", "存储类型"),
    STORAGE_ACCESS_TYPE("STORAGE_ACCESS_TYPE", "文件访问类型"),
    MOBILE_COUNTRY_CODE_TYPE("MOBILE_COUNTRY_CODE_TYPE", "国家地区区号"),
    CAPTCHA_TYPE("CAPTCHA_TYPE", "验证码类型"),
    SSL_PROTOCOL_TYPE("SSL_PROTOCOL_TYPE", "SSL协议类型"),
    SOCIAL_ACCOUNT_TYPE("SOCIAL_ACCOUNT_TYPE", "社交账号类型"),
    SELENIUM_DRIVER_TYPE("SELENIUM_DRIVER_TYPE", "浏览器驱动类型"),
    NONE("NONE", "NONE");

    private final String value;
    private final String description;
}
