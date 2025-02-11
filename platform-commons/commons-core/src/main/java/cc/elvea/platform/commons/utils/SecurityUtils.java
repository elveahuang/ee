package cc.elvea.platform.commons.utils;

import cc.elvea.platform.commons.constants.SecurityConstants;
import cc.elvea.platform.commons.core.security.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Objects;

/**
 * @author elvea
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
     * 获取用户ID
     */
    public static User getUser(Authentication authentication) {
        if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof User user) {
            return user;
        }
        return null;
    }

    /**
     * 获取用户ID
     */
    public static User getUser() {
        return getUser(getAuthentication());
    }

    /**
     * 获取会话ID
     */
    public static String getSid() {
        return getSid(getAuthentication());
    }

    /**
     * 获取会话ID
     */
    public static String getSid(Authentication authentication) {
        if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getId();
        }
        return null;
    }

    /**
     * 获取用户ID
     */
    public static Long getUid(Authentication authentication) {
        if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof User user) {
            return user.getId();
        } else if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim(SecurityConstants.JWT_KEY_UID);
        }
        return 0L;
    }

    /**
     * 获取用户ID
     */
    public static Long getUid() {
        return getUid(getAuthentication());
    }

    /**
     * 获取用户名
     */
    public static String getUsername(Authentication authentication) {
        if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof User user) {
            return user.getUsername();
        } else if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim(SecurityConstants.JWT_KEY_USERNAME);
        } else if (!ObjectUtils.isEmpty(authentication)) {
            return authentication.getName();
        }
        return null;
    }

    /**
     * 获取用户名
     */
    public static String getUsername() {
        return getUsername(getAuthentication());
    }

    /**
     * 是否为系统管理员
     */
    public static boolean isAdmin() {
        return isAdmin(getUid());
    }

    /**
     * 是否为系统管理员
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 未登陆则表明是匿名用户
     *
     * @return boolean
     */
    public static boolean isAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    /**
     * 当前是否已经登录
     *
     * @return boolean
     */
    public static boolean isAuthenticated() {
        return !Objects.isNull(SecurityContextHolder.getContext().getAuthentication())
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    /**
     * 密码加密
     *
     * @param password 明文密码
     * @return 加密密码
     */
    public static String encode(String password) {
        PasswordEncoder encoder = SpringUtils.getBean(PasswordEncoder.class);
        return encoder.encode(password);
    }

    /**
     * 密码比对
     *
     * @param rawPassword     明文密码
     * @param encodedPassword 明文密码
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        PasswordEncoder encoder = SpringUtils.getBean(PasswordEncoder.class);
        return encoder.matches(rawPassword, encodedPassword);
    }

}
