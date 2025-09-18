package cc.wdev.dev.webapp;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author elvea
 */
@Slf4j
@SpringBootApplication
@EntityScan(basePackages = {
    "cc.wdev.dev.**.entity",
})
@EnableJpaRepositories(basePackages = {
    "cc.wdev.dev.**.repository",
}, repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class WebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

}
