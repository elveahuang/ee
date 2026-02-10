package cc.wdev.platform.commons.core.ai.domain.request;

import cc.wdev.platform.commons.web.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.ai.chat.messages.Message;

import java.util.List;
import java.util.Map;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SimpleCompletionRequest extends Request {
    /**
     * 系统消息
     */
    private String systemPrompt;
    /**
     * 用户消息
     */
    private String prompt;
    /**
     * 消息列表
     */
    private List<Message> messages;
    /**
     * 消息列表
     */
    private List<Object> tools;
    /**
     * 工具上下文
     */
    private Map<String, Object> contextMap;
}
