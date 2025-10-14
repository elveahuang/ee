package cc.wdev.platform.gateway;

import cc.wdev.platform.system.core.feign.TenantFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author elvea
 */
@Slf4j
@EnableFeignClients(basePackageClasses = {
    TenantFeignClient.class
})
@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

}
