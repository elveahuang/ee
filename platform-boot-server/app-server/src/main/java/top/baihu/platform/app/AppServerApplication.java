package top.baihu.platform.app;

import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.spring.boot.starter.actuate.CosIdEndpointAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;

/**
 * @author elvea
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {
    "top.baihu.platform.commons",
    "top.baihu.platform.security",
    "top.baihu.platform.system",
}, exclude = {
    DataSourceAutoConfiguration.class,
    CosIdEndpointAutoConfiguration.class
})
@EntityScan(basePackages = {
    "top.baihu.platform.system.**.entity",
})
@EnableJpaRepositories(basePackages = {
    "top.baihu.platform.system.**.repository",
}, repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class AppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppServerApplication.class, args);
    }

}
