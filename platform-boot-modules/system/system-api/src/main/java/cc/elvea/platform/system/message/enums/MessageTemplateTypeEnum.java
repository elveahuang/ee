package cc.elvea.platform.system.message.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum MessageTemplateTypeEnum implements BaseEnum<String> {
    NOTICE("NOTICE", "系统通知"),
    MAIL("MAIL", "邮件"),
    SMS("SMS", "短信"),
    WECHAT("WECHAT", "微信"),
    WEWORK("WEWORK", "企微"),
    LARK("LARK", "飞书"),
    DINGTALK("DINGTALK", "钉钉");

    private final String code;
    private final String description;

    MessageTemplateTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return ("label_message_template_type__".concat(this.code)).toLowerCase();
    }

}
