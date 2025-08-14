package top.baihu.platform.commons.core.ai.domain.request;

import lombok.*;
import top.baihu.platform.commons.web.request.Request;

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
