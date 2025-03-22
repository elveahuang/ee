package cc.elvea.platform.system.message.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum MessageTargetTypeEnum implements BaseEnum<Integer> {
    IMMEDIATE(1, "IMMEDIATE", "立即发送"),
    FIXED(2, "FIXED", "定时发送"),
    AUTO(3, "AUTO", "跟谁系统");

    private final Integer value;
    private final String code;
    private final String description;

    MessageTargetTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_message_target_type__".concat(this.code)).toLowerCase();
    }

}
