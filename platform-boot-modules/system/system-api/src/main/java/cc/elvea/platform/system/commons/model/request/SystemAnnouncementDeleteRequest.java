package cc.elvea.platform.system.commons.model.request;

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
public class SystemAnnouncementDeleteRequest extends Request {
    private Long[] ids;
}
