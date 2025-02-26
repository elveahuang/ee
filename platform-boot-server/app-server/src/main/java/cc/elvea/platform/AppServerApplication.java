package cc.elvea.platform;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.spring.boot.starter.actuate.CosIdEndpointAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author elvea
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {
        "cc.elvea.platform.configuration",
        "cc.elvea.platform.commons",
        "cc.elvea.platform.lxp",
        "cc.elvea.platform.security",
        "cc.elvea.platform.system",
}, exclude = {
        DataSourceAutoConfiguration.class,
        CosIdEndpointAutoConfiguration.class
})
@EntityScan(basePackages = {
        "cc.elvea.platform.lxp.**.entity",
        "cc.elvea.platform.system.**.entity",
})
@EnableJpaRepositories(basePackages = {
        "cc.elvea.platform.lxp.**.repository",
        "cc.elvea.platform.system.**.repository",
}, repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class AppServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AppServerApplication.class, args);
        Environment env = context.getEnvironment();
        log.info("Application {} started. ", env.getProperty("spring.application.name", ""));
        log.info("Application version - {}. ", GlobalConstants.VERSION);
    }

}
