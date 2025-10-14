package cc.wdev.platform.gateway.configuration;

import cc.wdev.platform.commons.core.tenant.Tenant;
import cc.wdev.platform.commons.core.tenant.TenantStore;
import cc.wdev.platform.system.core.domain.dto.TenantDto;
import cc.wdev.platform.system.core.feign.TenantFeignClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class TenantConfiguration {

    private final TenantFeignClient tenantFeignClient;

    @Bean
    public TenantStore tenantStore() {
        return new TenantStore() {

            @Override
            public Tenant findById(Long id) {
                TenantDto tenantDto = tenantFeignClient.findById(id);
                if (tenantDto != null) {
                    return Tenant.builder().id(tenantDto.getId()).build();
                }
                return Tenant.builder().id(1000001L).build();
            }

            @Override
            public Tenant findByCode(String code) {
                log.info("Finding tenant by code: {}", code);

                TenantDto tenantDto = tenantFeignClient.findByCode(code);
                if (tenantDto != null) {
                    return Tenant.builder().id(tenantDto.getId()).build();
                }
                return Tenant.builder().id(1000001L).build();
            }

        };
    }

}
