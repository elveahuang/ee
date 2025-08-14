package top.baihu.platform.system.core.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.baihu.platform.commons.web.request.Request;

/**
 * @author elvea
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserDeleteRequest extends Request {
    private Long[] ids;
}
