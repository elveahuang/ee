package cc.wdev.platform.commons.core.ai.domain.request;

import cc.wdev.platform.commons.web.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SimpleCompletionRequest extends Request {
    private String prompt;
}
