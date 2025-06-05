package cc.elvea.platform.system;

import cc.elvea.platform.commons.constants.GlobalConstants;
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
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SystemServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SystemServerApplication.class, args);
        Environment env = context.getEnvironment();
        log.info("Application {} started. ", env.getProperty("spring.application.name", ""));
        log.info("Application version - {}. ", GlobalConstants.VERSION);
    }

}
