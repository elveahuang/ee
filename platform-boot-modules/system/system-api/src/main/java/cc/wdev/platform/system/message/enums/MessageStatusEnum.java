package cc.wdev.platform.system.message.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.enums.BizGroupTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum MessageStatusEnum implements BaseBizTypeEnum<Integer> {
    PENDING(1, "PENDING", "等待发送"),
    SENT(2, "SENT", "已发送"),
    SENDING(3, "SENDING", "发送中"),
    FAIL(4, "FAIL", "发送失败");

    private final Integer value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.MESSAGE_STATUS_TYPE.getValue();
    }

}
