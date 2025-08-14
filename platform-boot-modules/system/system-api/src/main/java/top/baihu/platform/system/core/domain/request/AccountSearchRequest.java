package top.baihu.platform.system.core.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.baihu.platform.commons.web.request.PageRequest;

/**
 * @author elvea
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class AccountSearchRequest extends PageRequest {
}
