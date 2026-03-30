package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 聊天消息类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum ChatMessageBizTypeEnum implements BaseBizTypeEnum<String> {
    IM("IM", "IM");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.CHAT_MESSAGE_TYPE.getValue().toUpperCase();
    }

}
