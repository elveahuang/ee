package cc.wdev.platform.system.core.manager;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.domain.dto.LoginSessionDto;

/**
 * @author elvea
 */
public interface LoginSessionManager {

    R<Boolean> saveUserSession(LoginSessionDto dto) throws Exception;

}
