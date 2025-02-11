package cc.elvea.platform.system.core.model.request;

import cc.elvea.platform.commons.web.request.Request;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserDeleteRequest extends Request {
    private Long[] ids;
}
