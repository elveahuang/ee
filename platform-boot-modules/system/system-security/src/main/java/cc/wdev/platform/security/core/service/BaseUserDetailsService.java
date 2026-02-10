package cc.wdev.platform.security.core.service;

import cc.wdev.platform.commons.security.user.OtpUser;
import cc.wdev.platform.commons.security.user.SocialUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author elvea
 */
public interface BaseUserDetailsService extends UserDetailsService {

    UserDetails loadUserBySocial(SocialUser socialUser) throws UsernameNotFoundException;

    UserDetails loadUserByOtp(OtpUser smsUser) throws UsernameNotFoundException;

}
