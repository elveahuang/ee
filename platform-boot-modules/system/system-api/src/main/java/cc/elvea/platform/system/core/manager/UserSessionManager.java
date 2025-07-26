package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.commons.web.response.R;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 */
public interface UserSessionManager {

    R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception;

}
