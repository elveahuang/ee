package cc.wdev.dev.webapp.configuration;

import cc.wdev.dev.webapp.constants.SystemConstants;
import cc.wdev.platform.commons.constants.SecurityConstants;
import cc.wdev.platform.commons.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static cc.wdev.platform.commons.constants.SecurityConstants.WEB_EXCLUDE_URLS;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        log.info("Creating defaultSecurityFilterChain for App Server");
        log.info("defaultSecurityFilterChain Whitelist path : [{}]", JacksonUtils.toJson(WEB_EXCLUDE_URLS));
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(SecurityConstants.WEB_EXCLUDE_URLS).permitAll()
                .requestMatchers(SystemConstants.WEB_EXCLUDE_URLS).permitAll()
                .anyRequest().permitAll());
        return http.build();
    }


}
