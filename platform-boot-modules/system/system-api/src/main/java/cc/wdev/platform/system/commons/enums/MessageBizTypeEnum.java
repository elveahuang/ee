package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息业务类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum MessageBizTypeEnum implements BaseBizTypeEnum<String> {
    TEST("TEST", "测试消息"),
    CAPTCHA("CAPTCHA", "验证码消息"),
    REGISTER_SUCCESS("REGISTER_SUCCESS", "注册成功消息");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.MESSAGE_TYPE.getValue().toUpperCase();
    }

}
