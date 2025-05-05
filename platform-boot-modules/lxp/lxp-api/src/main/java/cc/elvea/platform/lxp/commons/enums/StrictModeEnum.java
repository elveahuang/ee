package cc.elvea.platform.lxp.commons.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum StrictModeEnum implements BaseEnum<Integer> {
    ON(1, "ON", "开启严格模式"),
    OFF(0, "OFF", "宽松模式");

    private final static String LABEL_PREFIX = "label_lxp_strict_mode__";

    private final Integer value;
    private final String code;
    private final String description;

    StrictModeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return LABEL_PREFIX.concat(this.code.toLowerCase());
    }

}
