package cc.elvea.platform.system.announcement.model.request;

import cc.elvea.platform.commons.web.request.PageRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SystemAnnouncementSearchRequest extends PageRequest {
}
