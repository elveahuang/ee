package cc.wdev.platform.security.core.service;

import cc.wdev.platform.commons.enums.MobileCountryCodeTypeEnum;
import cc.wdev.platform.commons.enums.UserTypeEnum;
import cc.wdev.platform.commons.security.user.OtpUser;
import cc.wdev.platform.commons.security.user.SocialUser;
import cc.wdev.platform.commons.security.user.User;
import cc.wdev.platform.commons.utils.RegexUtils;
import cc.wdev.platform.system.core.api.AccountApi;
import cc.wdev.platform.system.core.domain.dto.AccountLoginDto;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author elvea
 * @see UserDetailsService
 */
@Slf4j
@AllArgsConstructor
@Service
@Qualifier("accountDetailsService")
public class CustomAccountDetailsService implements BaseUserDetailsService {

    private final AccountApi accountApi;

    /**
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        AccountLoginDto account;
        if (RegexUtils.checkEmail(username)) {
            log.info("findByEmail [Account] by [{}]", username);
            account = accountApi.findByEmail(username);
        } else if (RegexUtils.checkMobile(username)) {
            log.info("findByMobile [Account] by [{}]", username);
            account = accountApi.findByMobile(MobileCountryCodeTypeEnum.ZH_CN.getValue(), username);
        } else {
            log.info("findByUsername [Account] by [{}]", username);
            account = accountApi.findByUsername(username);
        }
        if (null == account) {
            throw new UsernameNotFoundException(username);
        }
        log.info("loadUserByUsername [Account] by [{}]. retrieved.", username);

        // 账号体系认证时查询相对应的会员开通记录
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        return new User(account.getId(), UserTypeEnum.ACCOUNT.getValue(), account.getUsername(), account.getPassword(), authorities);
    }

    /**
     * @see BaseUserDetailsService#loadUserBySocial(SocialUser)
     */
    @Override
    public UserDetails loadUserBySocial(SocialUser socialUser) {
        return null;
    }

    /**
     * @see BaseUserDetailsService#loadUserByOtp(OtpUser)
     */
    @Override
    public UserDetails loadUserByOtp(OtpUser otpUser) {
        return null;
    }

}
