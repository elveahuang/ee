package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.system.core.domain.dto.UserSessionDto;

/**
 * @author elvea
 */
public interface UserSessionManager {

    R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception;

}
