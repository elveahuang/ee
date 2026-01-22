package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum EntityOpenIdBizTypeEnum implements BaseEnum<String> {
    NONE("NONE", "未指定");

    private final String value;
    private final String description;
}
