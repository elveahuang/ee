package cn.elvea.platform.system.core.api;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface UserSessionApi {

    R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception;

}
