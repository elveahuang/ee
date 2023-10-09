package cn.elvea.platform.system.message.enums;

import cn.elvea.platform.commons.core.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum MessageTypeEnum implements BaseEnum<Integer> {
    NOTICE(1, "NOTICE", "系统通知"),
    SMS(2, "SMS", "短信"),
    MAIL(3, "MAIL", "邮件"),
    LARK(4, "LARK", "飞书消息"),
    WEWORK(5, "WEWORK", "企微消息"),
    WECHAT(6, "WECHAT", "微信消息"),
    DING_TALK(7, "DINGTALK", "钉钉消息");

    private final Integer value;
    private final String code;
    private final String description;

    MessageTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_message_type__".concat(this.code)).toLowerCase();
    }

}
