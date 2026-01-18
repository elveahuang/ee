package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum EntityRelationBizTypeEnum implements BaseEnum<String> {
    ORG_PARENT_ORG("ORG_PARENT_ORG", "组织<->组织关联"),
    PST_PARENT_PST("PST_PARENT_PST", "岗位<->岗位关联"),
    USR_CURRENT_ORG("USR_CURRENT_ORG", "用户<->组织关联"),
    USR_CURRENT_PST("USR_CURRENT_PST", "用户<->岗位关联");

    private final String value;
    private final String description;
}
