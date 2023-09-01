package cn.elvea.platform.commons.core.utils;

import cn.elvea.platform.commons.core.security.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class SecurityUtils {

    public static String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotEmpty(header) && header.startsWith("Bearer ")) {
            return header.split(" ")[1].trim();
        }
        return null;
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户
     */
    public static User getUser(Authentication authentication) {
        if (!ObjectUtils.isEmpty(authentication)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                return (User) principal;
            }
        }
        return null;
    }

    /**
     * 获取当前用户
     */
    public static User getUser() {
        return getUser(getAuthentication());
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId(Authentication authentication) {
        User user = getUser(authentication);
        if (user != null) {
            return user.getId();
        }
        return 0L;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUserId(getAuthentication());
    }

    /**
     * 获取用户名
     */
    public static String getUserName(Authentication authentication) {
        User user = getUser(authentication);
        if (user != null) {
            return user.getUsername();
        } else {
            return authentication.getName();
        }
    }

    /**
     * 获取用户名
     */
    public static String getUserName() {
        return getUserName(getAuthentication());
    }

    /**
     * 未登陆则表明是匿名用户
     *
     * @return boolean
     */
    public static boolean isAnonymous() {
        return Objects.isNull(SecurityContextHolder.getContext().getAuthentication()) ||
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

}
