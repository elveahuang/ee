package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 开放平台类型
 *
 * @author elvea
 */
@Getter
public enum OpenIdTypeEnum implements BaseEnum<String> {
    WECHAT("WECHAT", "微信"),
    WXWORK("WXWORK", "企微"),
    FEISHU("FEISHU", "飞书"),
    DINGTALK("DINGTALK", "钉钉"),
    QQ("QQ", "QQ"),
    GITHUB("GITHUB", "Github"),
    GOOGLE("GOOGLE", "Google");

    private final String code;
    private final String description;

    OpenIdTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_vip_biz_type__" + this.code.toLowerCase();
    }

}
