package top.baihu.platform.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import top.baihu.platform.commons.constants.GlobalConstants;
import top.baihu.platform.system.core.api.UserApi;
import top.baihu.platform.system.core.api.UserSessionApi;
import top.baihu.platform.system.security.api.AuthorizationApi;
import top.baihu.platform.system.security.api.AuthorizationConsentApi;
import top.baihu.platform.system.security.api.ClientApi;

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
