package cc.elvea.platform.system.core.model.request;

import cc.elvea.platform.commons.web.request.PageRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class AccountSearchRequest extends PageRequest {
}
