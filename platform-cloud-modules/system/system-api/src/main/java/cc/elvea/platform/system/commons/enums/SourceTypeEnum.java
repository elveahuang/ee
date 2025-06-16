package cc.elvea.platform.system.commons.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 数据来源类型
 *
 * @author elvea
 */
@Getter
public enum SourceTypeEnum implements BaseEnum<Integer> {
    SYSTEM(1, "SYSTEM", "内置"),
    NORMAL(2, "NORMAL", "普通"),
    IMP(3, "IMP", "导入"),
    SYNC(4, "SYNC", "同步");

    private final Integer value;
    private final String code;
    private final String description;

    SourceTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    public static SourceTypeEnum getSourceType(int value) {
        SourceTypeEnum[] ts = SourceTypeEnum.values();
        for (SourceTypeEnum t : ts) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return NORMAL;
    }

    public static SourceTypeEnum getSourceType(String code) {
        SourceTypeEnum[] ts = SourceTypeEnum.values();
        for (SourceTypeEnum t : ts) {
            if (t.getCode().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return NORMAL;
    }

    @Override
    public String getLabel() {
        return "label_source_type__" + this.code.toLowerCase();
    }

}
