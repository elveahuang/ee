package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * 国家地区区位码枚举
 *
 * @author elvea
 */
@Getter
public enum PlatformTypeEnum implements BaseEnum<String> {
    WECHAT("WECHAT", "微信"),
    WECOM("WECOM", "企微"),
    LARK("LARK", "飞书"),
    DINGTALK("DINGTALK", "飞书"),
    DESKTOP("WAP", "桌面浏览器"),
    WAP("WAP", "手机浏览器"),
    OTHERS("OTHERS", "其他");

    private final String code;
    private final String description;

    PlatformTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_platform__".concat(this.code.toLowerCase());
    }

}
