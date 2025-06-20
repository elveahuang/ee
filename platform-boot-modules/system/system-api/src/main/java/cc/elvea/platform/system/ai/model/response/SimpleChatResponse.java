package cc.elvea.platform.system.ai.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;

import java.io.Serializable;
import java.util.List;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleChatResponse implements Serializable {
    private String conversationId;
    private List<Message> messages;
}
