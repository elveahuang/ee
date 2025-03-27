package cc.elvea.platform.auth;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.system.core.api.UserApi;
import cc.elvea.platform.system.core.api.UserSessionApi;
import cc.elvea.platform.system.security.api.AuthorizationApi;
import cc.elvea.platform.system.security.api.AuthorizationConsentApi;
import cc.elvea.platform.system.security.api.ClientApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author elvea
 */
@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackageClasses = {
        UserApi.class, UserSessionApi.class, ClientApi.class, AuthorizationConsentApi.class, AuthorizationApi.class
})
@EnableDiscoveryClient
public class AuthServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AuthServerApplication.class, args);
        Environment env = context.getEnvironment();
        log.info("Application {} started. ", env.getProperty("spring.application.name", ""));
        log.info("Application version - {}. ", GlobalConstants.VERSION);
    }

}
