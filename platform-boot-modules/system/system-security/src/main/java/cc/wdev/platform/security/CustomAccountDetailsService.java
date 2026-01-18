package cc.wdev.platform.security;

import cc.wdev.platform.commons.enums.MobileCountryCodeTypeEnum;
import cc.wdev.platform.commons.enums.UserTypeEnum;
import cc.wdev.platform.commons.security.user.User;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.RegexUtils;
import cc.wdev.platform.system.core.api.AccountApi;
import cc.wdev.platform.system.core.domain.dto.AccountLoginInfoDto;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier("accountDetailsService")
public class CustomAccountDetailsService implements UserDetailsService {

    private final AccountApi accountApi;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        AccountLoginInfoDto account;
        if (RegexUtils.checkEmail(username)) {
            account = accountApi.findByEmail(username);
        } else if (RegexUtils.checkMobile(username)) {
            account = accountApi.findByMobile(MobileCountryCodeTypeEnum.ZH_CN.getValue(), username);
        } else {
            account = accountApi.findByUsername(username);
        }
        if (null == account) {
            throw new UsernameNotFoundException(username);
        }

        // 查询用户所有权限和所有角色，合并成统一的权限集合
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(account.getAuthorities())) {
            authorities.addAll(account.getAuthorities().stream().map(e -> new SimpleGrantedAuthority(e.getCode())).collect(Collectors.toSet()));
        }
        if (CollectionUtils.isNotEmpty(account.getRoles())) {
            authorities.addAll(account.getRoles().stream().map(e -> new SimpleGrantedAuthority(e.getCode())).collect(Collectors.toSet()));
        }
        return new User(account.getId(), UserTypeEnum.ACCOUNT.getValue(), account.getUsername(), account.getPassword(), authorities);
    }

}
