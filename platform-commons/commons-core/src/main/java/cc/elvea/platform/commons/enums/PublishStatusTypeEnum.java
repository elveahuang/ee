package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum PublishStatusTypeEnum implements BaseEnum<Integer> {
    ON(1, "ON", "已发布"),
    OFF(0, "OFF", "未发布");

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
        return "label_publish_status_type__".concat(this.code.toLowerCase());
    }

}
