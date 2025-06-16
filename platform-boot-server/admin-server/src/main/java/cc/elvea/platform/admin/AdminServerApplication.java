package cc.elvea.platform.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author elvea
 */
@Slf4j
public class AdminServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AdminServerApplication.class, args);

        Environment env = context.getEnvironment();
        log.info("Application {} started. ", env.getProperty("spring.application.name", "admin-server"));
    }

}
