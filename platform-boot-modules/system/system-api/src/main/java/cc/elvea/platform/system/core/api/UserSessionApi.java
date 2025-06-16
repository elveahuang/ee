package cc.elvea.platform.system.core.api;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 */
public interface UserSessionApi {

    R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception;

}
