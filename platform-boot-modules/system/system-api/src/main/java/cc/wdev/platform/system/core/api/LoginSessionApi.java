package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.domain.dto.LoginSessionDto;

/**
 * @author elvea
 */
public interface LoginSessionApi {

    R<Boolean> saveUserSession(LoginSessionDto dto) throws Exception;

}
