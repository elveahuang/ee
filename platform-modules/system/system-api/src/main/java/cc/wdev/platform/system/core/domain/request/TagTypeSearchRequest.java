package cc.wdev.platform.system.core.domain.request;

import cc.wdev.platform.commons.web.request.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagTypeSearchRequest extends PageRequest {
}
