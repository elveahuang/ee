package top.baihu.platform.system.core.manager;

import top.baihu.platform.commons.domain.R;
import top.baihu.platform.system.core.domain.dto.UserSessionDto;

/**
 * @author elvea
 */
public interface UserSessionManager {

    R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception;

}
