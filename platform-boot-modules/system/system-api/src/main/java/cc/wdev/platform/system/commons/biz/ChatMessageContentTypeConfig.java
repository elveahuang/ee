package cc.wdev.platform.system.commons.biz;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 聊天消息类型
 *
 * @author elvea
 */
@Data
@Builder
public class ChatMessageContentTypeConfig implements Serializable {
    private String type;
}
