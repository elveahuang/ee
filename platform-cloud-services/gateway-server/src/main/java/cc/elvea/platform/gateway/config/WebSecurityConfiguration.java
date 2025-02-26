package cc.elvea.platform.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author elvea
 */
@Slf4j
@EnableWebFluxSecurity()
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorizeExchange -> authorizeExchange
                .pathMatchers("/**").permitAll()
                .anyExchange().authenticated()
        ).csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

}
