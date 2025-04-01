package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * 启用状态类型枚举
 *
 * @author elvea
 */
@Getter
public enum ActiveTypeEnum implements BaseEnum<Integer> {
    ACTIVE(1, "ACTIVE", "启用状态为活动状态"),
    INACTIVE(0, "INACTIVE", "启用状态为闲置状态，标识数据已删除");

    private final static String LABEL_PREFIX = "label_active_type__";

    private final Integer value;
    private final String code;
    private final String description;

    ActiveTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return LABEL_PREFIX.concat(this.code.toLowerCase());
    }

}
