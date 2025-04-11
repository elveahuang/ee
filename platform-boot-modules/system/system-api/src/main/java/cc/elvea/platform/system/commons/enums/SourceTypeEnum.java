package cc.elvea.platform.system.commons.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 数据来源
 * <p>
 * 1. SYSTEM    - 系统内置，内置数据一般不允许删除
 * 2. NORMAL    - 普通数据
 * 3. IMPORT    - 导入数据
 * 3. SYNC      - 同步数据
 *
 * @author elvea
 */
@Getter
public enum SourceTypeEnum implements BaseEnum<Integer> {
    SYSTEM(1, "SYSTEM", "内置"),
    NORMAL(2, "NORMAL", "普通"),
    IMPORT(3, "IMPORT", "导入"),
    SYNC(4, "SYNC", "同步");

    private final Integer value;
    private final String code;
    private final String description;

    SourceTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_source_type__".concat(this.code)).toLowerCase();
    }

}
