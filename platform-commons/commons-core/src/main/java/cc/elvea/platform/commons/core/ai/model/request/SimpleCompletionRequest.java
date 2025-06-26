package cc.elvea.platform.commons.core.ai.model.request;

import cc.elvea.platform.commons.web.request.Request;
import lombok.*;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SimpleCompletionRequest extends Request {
    private String prompt;
}
