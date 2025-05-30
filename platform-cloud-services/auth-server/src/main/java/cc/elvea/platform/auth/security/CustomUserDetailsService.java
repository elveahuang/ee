package cc.elvea.platform.auth.security;

import cc.elvea.platform.commons.core.security.user.User;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.api.UserApi;
import cc.elvea.platform.system.core.model.dto.UserLoginDto;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserApi userApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginDto user = userApi.findByUsername(username).getData();
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
