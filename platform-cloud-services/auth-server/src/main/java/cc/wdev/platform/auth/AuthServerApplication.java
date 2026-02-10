package cc.wdev.platform.auth;

import cc.wdev.platform.commons.constants.GlobalConstants;
import cc.wdev.platform.system.core.feign.UserFeignClient;
import cc.wdev.platform.system.core.feign.UserSessionFeignClient;
import cc.wdev.platform.system.security.feign.AuthorizationConsentFeignClient;
import cc.wdev.platform.system.security.feign.AuthorizationFeignClient;
import cc.wdev.platform.system.security.feign.ClientFeignClient;
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
    UserFeignClient.class, UserSessionFeignClient.class, ClientFeignClient.class, AuthorizationConsentFeignClient.class, AuthorizationFeignClient.class
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
