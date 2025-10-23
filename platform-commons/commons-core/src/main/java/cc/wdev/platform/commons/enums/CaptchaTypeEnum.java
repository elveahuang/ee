package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证码类型枚举
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum CaptchaTypeEnum implements BaseBizTypeEnum<String> {
    CODE("CODE", "随机验证码"),
    SMS("SMS", "手机短信验证码"),
    MAIL("EMAIL", "电子邮件验证码");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.CAPTCHA_TYPE.getValue().toUpperCase();
    }

}
