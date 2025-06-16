package cc.elvea.platform.lxp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author elvea
 */
@Slf4j
@SpringBootApplication
public class LxpServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LxpServerApplication.class, args);
        Environment env = context.getEnvironment();
        log.info("Application {} started. ", env.getProperty("spring.application.name", ""));
    }

}
