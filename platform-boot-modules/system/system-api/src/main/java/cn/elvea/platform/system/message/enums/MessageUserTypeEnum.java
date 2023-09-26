package cn.elvea.platform.system.message.enums;

import cn.elvea.platform.commons.core.enums.BaseEnum;
import lombok.Getter;

/**
 * 消息发送类型枚举定义
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum MessageUserTypeEnum implements BaseEnum<Integer> {
    FROM(1, "FROM", "发送人"),
    TO(2, "TO", "接收人"),
    CC(3, "CC", "抄送人"),
    BCC(4, "BCC", "暗抄送人");

    private final Integer value;
    private final String code;
    private final String description;

    MessageUserTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_message_user_type__".concat(this.code)).toLowerCase();
    }

}
