package cc.wdev.platform.commons.autoconfigure.data;

import cc.wdev.platform.commons.autoconfigure.core.properties.TenancyProperties;
import cc.wdev.platform.commons.autoconfigure.data.properties.MyBatisCustomProperties;
import cc.wdev.platform.commons.data.mybatis.handler.CustomMetaObjectHandler;
import cc.wdev.platform.commons.data.mybatis.handler.CustomTenantHandler;
import cc.wdev.platform.commons.data.mybatis.id.CustomIdentifierGenerator;
import cc.wdev.platform.commons.utils.CollectionUtils;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MyBatisCustomProperties.class)
@ConditionalOnClass({MybatisPlusPropertiesCustomizer.class})
@ConditionalOnProperty(prefix = MyBatisCustomProperties.PREFIX, name = "enabled", havingValue = "true")
public class MyBatisCustomAutoConfiguration {

    TenancyProperties tenancyProperties;

    public MyBatisCustomAutoConfiguration(TenancyProperties tenancyProperties) {
        log.info("CustomMyBatisAutoConfiguration is enabled");
        this.tenancyProperties = tenancyProperties;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // MyBatis-Plus
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 主键生成采用自定义的雪花算法
     */
    @Bean("mpIdentifierGenerator")
    @ConditionalOnMissingBean
    public IdentifierGenerator mpIdentifierGenerator() {
        return new CustomIdentifierGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomMetaObjectHandler customMetaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomTenantHandler customTenantHandler() {
        List<String> tables = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(this.tenancyProperties.getIgnoreTables())) {
            tables.addAll(this.tenancyProperties.getIgnoreTables());
        }
        return new CustomTenantHandler(tables);
    }

    /**
     * 拦截器
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor(@Autowired(required = false) CustomTenantHandler customTenantHandler) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 多租户插件
        if (this.tenancyProperties != null && this.tenancyProperties.isEnabled()) {
            TenantLineInnerInterceptor tenantInterceptor = new TenantLineInnerInterceptor();
            tenantInterceptor.setTenantLineHandler(customTenantHandler);
            interceptor.addInnerInterceptor(tenantInterceptor);
        }
        return interceptor;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // MyBatis
    // -----------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            configuration.setLogImpl(StdOutImpl.class);
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }

}
