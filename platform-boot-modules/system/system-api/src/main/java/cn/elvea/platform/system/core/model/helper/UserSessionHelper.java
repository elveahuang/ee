package cn.elvea.platform.system.core.model.helper;

import cn.elvea.platform.commons.core.utils.ServletUtils;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 2.0.0
 */
@Slf4j
public abstract class UserSessionHelper {

    public static UserSessionDto userSession(long userId, String username, boolean success, HttpServletRequest request) {
        return UserSessionDto.builder()
                .userId(userId)
                .username(username)
                .success(success)
                .ua(ServletUtils.getUserAgent())
                .host(ServletUtils.getHost(request))
                .build();
    }

}
