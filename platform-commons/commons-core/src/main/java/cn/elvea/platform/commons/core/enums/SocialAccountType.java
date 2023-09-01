package cn.elvea.platform.commons.core.enums;

import lombok.Getter;

/**
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum SocialAccountType implements BaseEnum<String> {
    LARK("LARK", "飞书"),
    WECHAT("WECHAT", "微信"),
    WEWORK("WEWORK", "企微"),
    DINGTALK("DINGTALK", "钉钉");

    private final String code;
    private final String description;

    SocialAccountType(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_social_account_type__" + this.code.toLowerCase();
    }

}
