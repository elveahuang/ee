package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * @author elvea
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
