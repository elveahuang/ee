package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum SmsAccountTypeEnum implements BaseEnum<String> {
    MOBILE("MOBILE", "手机号"),
    EMAIL("EMAIL", "电子邮箱"),
    ;

    private final String value;
    private final String description;
}
