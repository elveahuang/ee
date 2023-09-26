package cn.elvea.platform.system.message.enums;

import cn.elvea.platform.commons.core.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum MessageTemplateTypeEnum implements BaseEnum<Integer> {
    NOTICE(1, "NOTICE", "系统通知"),
    MAIL(2, "MAIL", "邮件"),
    SMS(3, "SMS", "短信"),
    WECHAT(4, "WECHAT", "微信"),
    WEWORK(5, "WEWORK", "企微"),
    LARK(6, "LARK", "飞书"),
    DINGTALK(7, "DINGTALK", "钉钉");

    private final Integer value;
    private final String code;
    private final String description;

    MessageTemplateTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_message_template_type__".concat(this.code)).toLowerCase();
    }

}
