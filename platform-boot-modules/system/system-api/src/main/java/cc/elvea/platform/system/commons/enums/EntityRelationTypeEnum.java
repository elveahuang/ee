package cc.elvea.platform.system.commons.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum EntityRelationTypeEnum implements BaseEnum<String> {
    ORG_PARENT_ORG("ORG_PARENT_ORG", "组织<->组织关联"),
    PST_PARENT_PST("PST_PARENT_PST", "岗位<->岗位关联"),
    USR_CURRENT_ORG("USR_CURRENT_ORG", "用户<->组织关联"),
    USR_CURRENT_PST("USR_CURRENT_PST", "用户<->岗位关联");

    private final static String LABEL_PREFIX = "label_entity_relation_type__";

    private final String value;

    private final String description;

    EntityRelationTypeEnum(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return LABEL_PREFIX.concat(this.value.toLowerCase());
    }

}
