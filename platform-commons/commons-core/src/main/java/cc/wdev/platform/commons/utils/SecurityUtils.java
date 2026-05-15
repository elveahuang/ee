package cc.wdev.platform.commons.utils;

import cc.wdev.platform.commons.constants.SecurityConstants;
import cc.wdev.platform.commons.core.tenant.TenantContext;
import cc.wdev.platform.commons.security.domain.Role;
import cc.wdev.platform.commons.security.domain.User;
import com.google.common.collect.Sets;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
        } else if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof Jwt jwt) {
            Long uid = jwt.getClaim(SecurityConstants.JWT_KEY_UID);
            Long tid = jwt.getClaim(SecurityConstants.JWT_KEY_TID);
            String username = jwt.getClaimAsString(SecurityConstants.JWT_KEY_USERNAME);
            Set<GrantedAuthority> authorities = Sets.newHashSet(authentication.getAuthorities());
            return new User(tid, uid, username, null, authorities, Collections.emptySet());
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
     * 获取租户ID
     */
    public static Long getTid() {
        return getTid(getAuthentication());
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

    public static String getUidStr() {
        Long uid = getUid(getAuthentication());
        return String.valueOf(uid);
    }

    /**
     * 获取用户数据范围
     */
    public static Set<String> getDataScopes() {
        return getDataScopes(getAuthentication());
    }

    /**
     * 获取用户数据范围
     */
    public static Set<String> getDataScopes(Authentication authentication) {
        User user = getUser(authentication);
        if (user != null) {
            Set<String> scopes = Sets.newHashSet();
            if (CollectionUtils.isNotEmpty(user.getRoles())) {
                scopes.addAll(user.getRoles().stream().map(Role::getScopes).flatMap(Set::stream).collect(Collectors.toSet()));
            }
            return scopes;
        }
        return Collections.emptySet();
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
     * 获取租户ID
     */
    public static Long getTid(Authentication authentication) {
        if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof User user) {
            return user.getTenantId();
        } else if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim(SecurityConstants.JWT_KEY_TID);
        }
        return TenantContext.getTenantId();
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || authentication.getPrincipal() == null ||
            "anonymousUser".equals(authentication.getPrincipal()) || !authentication.isAuthenticated();
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

    /**
     * 获取密码编码器
     */
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
}
