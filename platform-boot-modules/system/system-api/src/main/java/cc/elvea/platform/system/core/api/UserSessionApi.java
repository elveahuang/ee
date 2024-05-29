package cc.elvea.platform.system.core.api;

import cc.elvea.platform.commons.web.R;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UserSessionApi {

    R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception;

}
