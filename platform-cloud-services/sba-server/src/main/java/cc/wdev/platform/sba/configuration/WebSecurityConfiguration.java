package cc.wdev.platform.sba.configuration;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfiguration {

    private final AdminServerProperties adminServer;

    public WebSecurityConfiguration(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    /**
     * 默认安全设置
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.path("/"));

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(this.adminServer.path("/assets/**")).permitAll()
                .requestMatchers(this.adminServer.path("/login")).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                .loginPage(this.adminServer.path("/login"))
                .successHandler(successHandler)
            )
            .logout(logout -> logout.logoutUrl(this.adminServer.path("/logout")))
            .httpBasic(Customizer.withDefaults())
            .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                    PathPatternRequestMatcher.withDefaults().matcher(POST, this.adminServer.path("/instances")),
                    PathPatternRequestMatcher.withDefaults().matcher(DELETE, this.adminServer.path("/instances/*")),
                    PathPatternRequestMatcher.withDefaults().matcher(this.adminServer.path("/actuator/*")))
            );
        return http.build();
    }

}
