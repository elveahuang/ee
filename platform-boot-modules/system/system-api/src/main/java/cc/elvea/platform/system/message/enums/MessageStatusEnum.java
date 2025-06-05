package cc.elvea.platform.system.message.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum MessageStatusEnum implements BaseEnum<Integer> {
    PENDING(1, "PENDING", "等待发送"),
    SENT(2, "SENT", "已发送"),
    SENDING(3, "SENDING", "发送中"),
    FAIL(4, "FAIL", "发送失败");

    private final Integer value;
    private final String code;
    private final String description;

    MessageStatusEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_message_type__".concat(this.code)).toLowerCase();
    }

}
