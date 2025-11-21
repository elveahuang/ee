package cc.wdev.platform.commons.message.sse.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SseRequestMsg {
    /**
     * 用户ID
     */
    String userId;
    /**
     * 对话ID
     */
    String conversationId;
    /**
     * 内容
     */
    String content;
}
