package cn.elvea.platform.system.core.api;

import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface UserApi {

    UserInfoDto getUserInfo(String username);

    UserLoginDto findByUsername(String username);

    UserLoginDto findByMobile(String mobileCountryCode, String mobileNumber);

    UserLoginDto findByEmail(String email);

}
