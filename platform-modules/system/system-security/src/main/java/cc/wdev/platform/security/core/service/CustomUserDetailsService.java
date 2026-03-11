package cc.wdev.platform.security.core.service;

import cc.wdev.platform.commons.enums.MobileCountryCodeTypeEnum;
import cc.wdev.platform.commons.enums.UserTypeEnum;
import cc.wdev.platform.commons.security.user.OtpUser;
import cc.wdev.platform.commons.security.user.SocialUser;
import cc.wdev.platform.commons.security.user.User;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.RegexUtils;
import cc.wdev.platform.system.core.api.UserApi;
import cc.wdev.platform.system.core.domain.dto.UserLoginDto;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see UserDetailsService
 */
@Slf4j
@Primary
@Service
@Qualifier("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements BaseUserDetailsService {

    private final UserApi userApi;

    /**
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        UserLoginDto user;
        if (RegexUtils.checkEmail(username)) {
            log.info("findByEmail [User] by [{}]", username);
            user = userApi.findByEmail(username);
        } else if (RegexUtils.checkMobile(username)) {
            log.info("findByMobile [User] by [{}]", username);
            user = userApi.findByMobile(MobileCountryCodeTypeEnum.ZH_CN.getValue(), username);
        } else {
            log.info("findByUsername [User] by [{}]", username);
            user = userApi.findByUsername(username);
        }
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        log.info("loadUserByUsername [User] by [{}]. retrieved.", username);

        // 查询用户所有权限和所有角色，合并成统一的权限集合
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(user.getAuthorities())) {
            authorities.addAll(user.getAuthorities().stream().map(e -> new SimpleGrantedAuthority(e.getCode())).collect(Collectors.toSet()));
        }
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            authorities.addAll(user.getRoles().stream().map(e -> new SimpleGrantedAuthority(e.getCode())).collect(Collectors.toSet()));
        }
        return new User(user.getId(), UserTypeEnum.USER.getValue(), user.getUsername(), user.getPassword(), authorities);
    }

    /**
     * @see BaseUserDetailsService#loadUserBySocial(SocialUser)
     */
    public UserDetails loadUserBySocial(SocialUser socialUser) {
        return null;
    }

    /**
     * @see BaseUserDetailsService#loadUserByOtp(OtpUser)
     */
    @Override
    public UserDetails loadUserByOtp(OtpUser smsUser) {
        return null;
    }

}
