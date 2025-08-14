package top.baihu.platform.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.enums.MobileCountryCodeEnum;
import top.baihu.platform.commons.security.user.User;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.commons.utils.RegexUtils;
import top.baihu.platform.system.core.domain.dto.UserLoginInfoDto;
import top.baihu.platform.system.core.manager.UserManager;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see UserDetailsService
 */
@Slf4j
@AllArgsConstructor
@Primary
@Service
@Qualifier("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginInfoDto user;
        if (RegexUtils.checkEmail(username)) {
            user = userManager.findByEmail(username);
        } else if (RegexUtils.checkMobile(username)) {
            user = userManager.findByMobile(MobileCountryCodeEnum.ZH_CN.getCode(), username);
        } else {
            user = userManager.findByUsername(username);
        }
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }

        // 查询用户所有权限和所有角色，合并成统一的权限集合
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(user.getAuthorities())) {
            authorities.addAll(user.getAuthorities().stream().map(e -> new SimpleGrantedAuthority(e.getCode())).collect(Collectors.toSet()));
        }
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            authorities.addAll(user.getRoles().stream().map(e -> new SimpleGrantedAuthority(e.getCode())).collect(Collectors.toSet()));
        }
        return new User(user.getId(), user.getUsername(), user.getPassword(), authorities);
    }

}
