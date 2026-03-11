package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 目录关联业务类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum CatalogRelationBizTypeEnum implements BaseEnum<String> {
    NONE("NONE", "NONE");

    private final String value;
    private final String description;
}
