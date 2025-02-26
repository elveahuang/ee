package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * 验证码类型枚举
 *
 * @author elvea
 */
@Getter
public enum CaptchaTypeEnum implements BaseEnum<String> {
    CODE("CODE", "随机验证码"),
    SMS("SMS", "手机短信验证码"),
    MAIL("EMAIL", "电子邮件验证码");

    private final String code;
    private final String description;

    CaptchaTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_captcha_type__".concat(this.code.toLowerCase());
    }

}
