package cc.elvea.platform.system.core.helper;


import cc.elvea.platform.commons.utils.ServletUtils;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 2.0.0
 */
@Slf4j
public abstract class UserSessionHelper {

    public static UserSessionDto userSession(String username, boolean success, HttpServletRequest request) {
        return UserSessionHelper.userSession(0L, username, success, request);
    }

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
