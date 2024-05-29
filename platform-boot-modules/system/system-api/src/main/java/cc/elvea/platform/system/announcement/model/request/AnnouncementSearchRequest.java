package cc.elvea.platform.system.announcement.model.request;

import cc.elvea.platform.commons.web.request.PageRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class AnnouncementSearchRequest extends PageRequest {
}
