package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息模版类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum MessageTemplateBizTypeEnum implements BaseBizTypeEnum<String> {
    NOTICE("NOTICE", "通知"),
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
