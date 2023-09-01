package cn.elvea.platform.gateway;

import cn.elvea.platform.commons.core.constants.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayServerApplication.class, args);
        Environment env = context.getEnvironment();
        log.info("Application {} started. ", env.getProperty("spring.application.name", ""));
        log.info("Application version - {}. ", GlobalConstants.VERSION);
    }

}
