package top.baihu.platform.system.core.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.baihu.platform.commons.web.request.PageRequest;

/**
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagTypeSearchRequest extends PageRequest {
}
