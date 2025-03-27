package cc.elvea.platform.system.core.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 数据范围
 *
 * @author elvea
 */
@Getter
public enum DataScoreTypeEnum implements BaseEnum<String> {
    ALL("ALL", "所有"),
    CURRENT_LEVEL_WITHOUT_CHILDREN("CURRENT_LEVEL_WITHOUT_CHILDREN", "本级"),
    CURRENT_LEVEL_WITH_CHILDREN("CURRENT_LEVEL_WITH_CHILDREN", "本级以及子级"),
    CUSTOMIZE("CUSTOMIZE", "自定义"),
    SELF("SELF", "个人");

    private final String code;
    private final String description;

    DataScoreTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static DataScoreTypeEnum getAuthorityType(String code) {
        DataScoreTypeEnum[] ts = DataScoreTypeEnum.values();
        for (DataScoreTypeEnum t : ts) {
            if (t.getCode().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return SELF;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_authority_type__" + this.code.toLowerCase();
    }

}
