package cc.elvea.platform;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.spring.boot.starter.actuate.CosIdEndpointAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author elvea
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {
    "cc.elvea.platform.configuration",
    "cc.elvea.platform.commons",
    "cc.elvea.platform.security",
    "cc.elvea.platform.system",
}, exclude = {
    DataSourceAutoConfiguration.class,
    CosIdEndpointAutoConfiguration.class
})
@EntityScan(basePackages = {
    "cc.elvea.platform.system.**.entity",
})
@EnableJpaRepositories(basePackages = {
    "cc.elvea.platform.system.**.repository",
}, repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class AppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppServerApplication.class, args);
    }

}
