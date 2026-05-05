package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统配置类型
 *
 * @author erden
 */
@Getter
@AllArgsConstructor
public enum ConfigBizTypeEnum implements BaseBizTypeEnum<String> {
    LOGIN_CAPTCHA_ENABLED("LOGIN_CAPTCHA_ENABLED", BizScopeTypeEnum.SYSTEM.getCode(), ConfigGroupTypeEnum.CONFIG.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "是否启用登录验证码"),
    // 访问限制
    ACCESS_LIMIT_ENABLED("ACCESS_LIMIT_ENABLED", BizScopeTypeEnum.SYSTEM.getCode(), ConfigGroupTypeEnum.ACCESS.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "是否开启访问限制"),
    ACCESS_LIMIT_COUNTRY("ACCESS_LIMIT_COUNTRY", BizScopeTypeEnum.SYSTEM.getCode(), ConfigGroupTypeEnum.ACCESS.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "访问限制国家"),
    ACCESS_LIMIT_TYPE("ACCESS_LIMIT_TYPE", BizScopeTypeEnum.SYSTEM.getCode(), ConfigGroupTypeEnum.ACCESS.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "访问限制类型"),
    ACCESS_LIMIT_MESSAGE("ACCESS_LIMIT_MESSAGE", BizScopeTypeEnum.SYSTEM.getCode(), ConfigGroupTypeEnum.ACCESS.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "访问限制提示消息"),
    ACCOUNT_INVITE_COUNT_MAX("ACCOUNT_INVITE_COUNT_MAX", BizScopeTypeEnum.SYSTEM.getCode(), ConfigGroupTypeEnum.ACCESS.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "账号邀请上限"),
    // 应用基本信息
    APP_TITLE("APP_TITLE", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.BASE.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "站点标题"),
    APP_ABOUT("APP_ABOUT", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.BASE.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "站点关于"),
    APP_COPYRIGHT("APP_COPYRIGHT", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.BASE.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "站点版权"),
    APP_CONTACT("APP_CONTACT", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.BASE.getValue(), ConfigContentTypeEnum.PAGE.getCode(), "联系我们"),
    APP_AGREEMENT_MEMBER("APP_AGREEMENT_MEMBER", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.AGREEMENT.getValue(), ConfigContentTypeEnum.PAGE.getCode(), "会员协议（针对付费用户）"),
    APP_AGREEMENT_USER("APP_AGREEMENT_USER", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.AGREEMENT.getValue(), ConfigContentTypeEnum.PAGE.getCode(), "用户协议（针对注册用户）"),
    APP_AGREEMENT_PRIVACY_POLICY("APP_AGREEMENT_PRIVACY_POLICY", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.AGREEMENT.getValue(), ConfigContentTypeEnum.PAGE.getCode(), "隐私政策"),
    // 微信公众号
    WEIXIN_MP_APP_ENABLED("WEIXIN_MP_APP_ENABLED", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.BOOL.getCode(), "邮件服务器是否开启安全协议"),
    WEIXIN_MP_APP_ID("WEIXIN_MP_APP_ID", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.WEIXIN_MP.getValue(), ConfigContentTypeEnum.BOOL.getCode(), "邮件服务器是否开启安全协议"),
    WEIXIN_MP_APP_SECRET("WEIXIN_MP_APP_SECRET", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.WEIXIN_MP.getValue(), ConfigContentTypeEnum.BOOL.getCode(), "邮件服务器是否开启安全协议"),
    WEIXIN_MP_APP_TOKEN("WEIXIN_MP_APP_TOKEN", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.WEIXIN_MP.getValue(), ConfigContentTypeEnum.BOOL.getCode(), "邮件服务器是否开启安全协议"),
    WEIXIN_MP_APP_AESKEY("WEIXIN_MP_APP_AESKEY", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.WEIXIN_MP.getValue(), ConfigContentTypeEnum.BOOL.getCode(), "邮件服务器是否开启安全协议"),
    // 邮件服务器
    MAIL_SERVER_ENABLED("MAIL_SERVER_ENABLED", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.BOOL.getCode(), "邮件服务器是否开启安全协议"),
    MAIL_SERVER_SSL("MAIL_SERVER_HOST", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.BOOL.getCode(), "邮件服务器是否开启安全协议"),
    MAIL_SERVER_HOST("MAIL_SERVER_HOST", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "邮件服务器主机"),
    MAIL_SERVER_PORT("MAIL_SERVER_PORT", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "邮件服务器端口"),
    MAIL_SERVER_USER("MAIL_SERVER_USER", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "邮件服务器用户"),
    MAIL_SERVER_PASS("MAIL_SERVER_PASS", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.PASSWORD.getCode(), "邮件服务器密码"),
    MAIL_SERVER_FROM("MAIL_SERVER_FROM", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "邮件服务器发件人"),
    MAIL_SERVER_NAME("MAIL_SERVER_NAME", BizScopeTypeEnum.TENANT.getCode(), ConfigGroupTypeEnum.MAIL.getValue(), ConfigContentTypeEnum.TEXT.getCode(), "邮件服务器名称"),
    // 开发测试专用
    DEV_PASS_CAPTCHA("DEV_PASS_CAPTCHA", BizScopeTypeEnum.SYSTEM.getCode(), ConfigGroupTypeEnum.DEV.getValue(), ConfigContentTypeEnum.BOOL.getCode(), "是否跳过验证码");

    private final String value;
    private final String scope;
    private final String contentGroup;
    private final String contentType;
    private final String description;

    @Override
    public String getGroup() {
        return CoreBizGroupTypeEnum.CONFIG_TYPE.getValue().toUpperCase();
    }

    @Override
    public String getScope() {
        return this.scope;
    }

}
