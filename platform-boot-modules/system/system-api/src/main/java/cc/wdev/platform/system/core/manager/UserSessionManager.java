package cc.wdev.platform.system.core.manager;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.domain.dto.UserSessionDto;

/**
 * @author elvea
 */
public interface UserSessionManager {

    R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception;

}
