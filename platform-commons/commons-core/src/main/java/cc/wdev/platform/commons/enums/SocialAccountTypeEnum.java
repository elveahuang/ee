package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum SocialAccountTypeEnum implements BaseBizTypeEnum<String> {
    LARK("LARK", "飞书"),
    WECHAT("WECHAT", "微信"),
    WEWORK("WEWORK", "企微"),
    DINGTALK("DINGTALK", "钉钉");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.SOCIAL_ACCOUNT_TYPE.getValue().toUpperCase();
    }

}
