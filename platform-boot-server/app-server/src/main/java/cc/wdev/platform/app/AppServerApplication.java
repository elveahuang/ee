package cc.wdev.platform.app;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.spring.boot.starter.actuate.CosIdEndpointAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author elvea
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {
    "cc.wdev.platform.commons",
    "cc.wdev.platform.security",
    "cc.wdev.platform.system",
}, exclude = {
    DataSourceAutoConfiguration.class,
    CosIdEndpointAutoConfiguration.class
})
@EntityScan(basePackages = {
    "cc.wdev.platform.system.**.entity",
})
@EnableJpaRepositories(basePackages = {
    "cc.wdev.platform.system.**.repository",
}, repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class AppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppServerApplication.class, args);
    }

}
