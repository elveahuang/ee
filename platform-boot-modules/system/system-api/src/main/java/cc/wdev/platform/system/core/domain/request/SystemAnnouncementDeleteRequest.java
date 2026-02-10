package cc.wdev.platform.system.core.domain.request;

import cc.wdev.platform.commons.web.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SystemAnnouncementDeleteRequest extends Request {
    private Long[] ids;
}
