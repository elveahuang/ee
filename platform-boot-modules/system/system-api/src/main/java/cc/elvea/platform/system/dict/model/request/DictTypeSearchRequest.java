package cc.elvea.platform.system.dict.model.request;

import cc.elvea.platform.commons.web.request.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictTypeSearchRequest extends PageRequest {
}
