package cn.elvea.platform.system.announcement.model.request;

import cn.elvea.platform.commons.core.web.request.PageRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class AnnouncementSearchRequest extends PageRequest {
}
