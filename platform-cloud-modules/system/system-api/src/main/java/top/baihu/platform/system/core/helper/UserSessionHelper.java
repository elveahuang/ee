package top.baihu.platform.system.core.helper;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import top.baihu.platform.commons.utils.ServletUtils;
import top.baihu.platform.system.core.domain.dto.UserSessionDto;

/**
 * @author elvea
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
