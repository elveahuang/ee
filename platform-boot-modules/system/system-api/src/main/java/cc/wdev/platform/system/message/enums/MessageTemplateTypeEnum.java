package cc.wdev.platform.system.message.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.enums.BizGroupTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum MessageTemplateTypeEnum implements BaseBizTypeEnum<String> {
    NOTICE("NOTICE", "系统通知"),
    MAIL("MAIL", "邮件"),
    SMS("SMS", "短信"),
    WECHAT("WECHAT", "微信"),
    WEWORK("WEWORK", "企微"),
    LARK("LARK", "飞书"),
    DINGTALK("DINGTALK", "钉钉");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.MESSAGE_TEMPLATE_TYPE.getValue().toUpperCase();
    }

}
