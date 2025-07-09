package cc.elvea.platform.commons.core.ai.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleChatRequest implements Serializable {
    private String conversationId;
    private String prompt;
    private boolean force;
}
