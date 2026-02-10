package cc.wdev.platform.system.configuration;

import cc.wdev.platform.commons.core.tenant.Tenant;
import cc.wdev.platform.commons.core.tenant.TenantConfigCustomizer;
import cc.wdev.platform.commons.core.tenant.TenantStore;
import cc.wdev.platform.system.core.api.TenantApi;
import cc.wdev.platform.system.core.domain.converter.TenantConverter;
import cc.wdev.platform.system.core.domain.dto.TenantDto;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class SystemTenantConfiguration {

    private TenantApi tenantApi;

    @Bean
    public TenantStore tenantStore() {
        return new TenantStore() {

            @Override
            public Tenant root() {
                return Tenant.builder().id(1000001L).build();
            }

            @Override
            public Tenant findById(Long id) {
                try {
                    TenantDto tenantDto = tenantApi.findById(id);
                    if (tenantDto != null) {
                        return TenantConverter.INSTANCE.dto2Tenant(tenantDto);
                    }
                } catch (Exception e) {
                    log.error("Failed to find tenant by id: {}, error: {}", id, e.getMessage());
                }
                // 降级
                return Tenant.builder().id(1000001L).build();
            }

            @Override
            public Tenant findByCode(String code) {
                try {
                    TenantDto tenantDto = tenantApi.findByCode(code);
                    if (tenantDto != null) {
                        return TenantConverter.INSTANCE.dto2Tenant(tenantDto);
                    }
                } catch (Exception e) {
                    log.error("Failed to find tenant by code: {}, error: {}", code, e.getMessage());
                }
                return Tenant.builder().id(1000001L).build();
            }

        };
    }

    /**
     * 自定义多租户配置，用于设置需要排除的表，主要针对MyBatisPlus的多租户插件
     */
    @Bean("systemTenantConfigCustomizer")
    public TenantConfigCustomizer systemTenantConfigCustomizer() {
        return config -> {
            List<String> excludes = Lists.newArrayList();
            // 系统模块中不需要租户隔离的表
            excludes.add("sys_authority");
            excludes.add("sys_attachment_type");
            excludes.add("sys_ai_chat_type");
            excludes.add("sys_client");
            excludes.add("sys_identity");
            excludes.add("sys_job_param");
            excludes.add("sys_job");
            excludes.add("sys_label");
            excludes.add("sys_lang");
            excludes.add("sys_order_type");
            excludes.add("sys_package_item");
            excludes.add("sys_package_authority");
            excludes.add("sys_package");
            excludes.add("sys_pay_type");
            excludes.add("sys_pay_type_item");
            excludes.add("sys_tenant");
            excludes.add("sys_url_log");

            config.getExcludes().addAll(excludes);
        };
    }

    @Lazy
    @Autowired
    public void setTenantApi(TenantApi tenantApi) {
        this.tenantApi = tenantApi;
    }

}
