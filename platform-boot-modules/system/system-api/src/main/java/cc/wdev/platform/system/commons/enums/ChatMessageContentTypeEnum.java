package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 聊天消息类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum ChatMessageContentTypeEnum implements BaseEnum<String> {
    TEXT("TEXT", "文本");

    private final String value;
    private final String description;
}
