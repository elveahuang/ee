package cc.wdev.platform.commons.core.ai.domain.request;

import cc.wdev.platform.commons.web.request.Request;
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
