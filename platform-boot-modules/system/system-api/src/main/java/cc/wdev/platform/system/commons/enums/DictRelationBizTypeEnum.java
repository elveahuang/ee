package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典关联业务类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum DictRelationBizTypeEnum implements BaseEnum<String> {
    BANNER("BANNER", "BANNER"),
    LINK("LINK", "LINK"),
    NONE("NONE", "NONE");

    private final String value;
    private final String description;
}
