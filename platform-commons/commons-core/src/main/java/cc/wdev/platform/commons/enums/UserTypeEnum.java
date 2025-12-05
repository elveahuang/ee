package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum implements BaseBizTypeEnum<String> {
    USER("USER", "用户"),
    ACCOUNT("ACCOUNT", "账号");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.USER_TYPE.getValue().toUpperCase();
    }

}
