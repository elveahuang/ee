package cc.elvea.platform.configuration;

import cc.elvea.platform.commons.utils.JacksonUtils;
import cc.elvea.platform.security.annotations.EnableCustomSecurity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static cc.elvea.platform.commons.constants.SecurityConstants.*;

/**
 * @author elvea
 */
@Slf4j
@EnableWebSecurity
@EnableCustomSecurity
@AllArgsConstructor
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfiguration {

    private final JwtDecoder jwtDecoder;

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    /**
     * 接口安全设置
     */
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        log.info("Creating apiSecurityFilterChain for App Server...");
        log.info("apiSecurityFilterChain Whitelist path : [{}].", JacksonUtils.toJson(API_EXCLUDE_URLS));
        return http.securityMatcher(API_REQUEST_PATH)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(API_EXCLUDE_URLS).permitAll()
                        .anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((resourceServerConfigurer) -> resourceServerConfigurer.jwt(jwtConfigurer -> {
                    jwtConfigurer.decoder(jwtDecoder);
                    jwtConfigurer.jwtAuthenticationConverter(this.jwtAuthenticationConverter);
                }))
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }

    /**
     * 默认安全设置
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        log.info("Creating defaultSecurityFilterChain for App Server...");
        log.info("defaultSecurityFilterChain Whitelist path : [{}].", JacksonUtils.toJson(WEB_EXCLUDE_URLS));
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WEB_EXCLUDE_URLS).permitAll()
                        .anyRequest().permitAll())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .oauth2ResourceServer((resourceServerConfigurer) -> resourceServerConfigurer.jwt(jwtConfigurer -> {
                    jwtConfigurer.decoder(jwtDecoder);
                    jwtConfigurer.jwtAuthenticationConverter(this.jwtAuthenticationConverter);
                }))
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
