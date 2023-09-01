package cn.elvea.platform.commons.core.enums;

import lombok.Getter;

/**
 * 发布状态类型枚举
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum PublishStatusTypeEnum implements BaseEnum<Integer> {
    ON(1, "ON", "已发布"),
    OFF(0, "OFF", "未发布");

    private final static String LABEL_PREFIX = "label_publish_status_type__";

    private final Integer value;
    private final String code;
    private final String description;

    PublishStatusTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return LABEL_PREFIX.concat(this.code.toLowerCase());
    }

}
