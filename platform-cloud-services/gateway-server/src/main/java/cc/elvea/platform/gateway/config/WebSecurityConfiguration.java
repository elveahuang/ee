package cc.elvea.platform.gateway.config;

import cc.elvea.platform.commons.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static cc.elvea.platform.commons.constants.SecurityConstants.WEB_EXCLUDE_URLS;

/**
 * @author elvea
 */
@Slf4j
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfiguration {

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        log.info("Creating defaultSecurityFilterChain for App Server");
        log.info("defaultSecurityFilterChain Whitelist path : [{}]", JacksonUtils.toJson(WEB_EXCLUDE_URLS));
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(WEB_EXCLUDE_URLS).permitAll()
                .anyRequest().permitAll());
        return http.build();
    }

}
