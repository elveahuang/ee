package cc.wdev.platform.commons.autoconfigure.data;

import cc.wdev.platform.commons.autoconfigure.core.CoreAutoConfiguration;
import cc.wdev.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.wdev.platform.commons.autoconfigure.data.properties.MyBatisCustomProperties;
import cc.wdev.platform.commons.data.mybatis.handler.CustomMetaObjectHandler;
import cc.wdev.platform.commons.data.mybatis.handler.CustomTenantLineHandler;
import cc.wdev.platform.commons.data.mybatis.id.CustomIdentifierGenerator;
import cc.wdev.platform.commons.utils.CollectionUtils;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@AutoConfiguration(after = {CoreAutoConfiguration.class})
@ConditionalOnClass({MybatisPlusAutoConfiguration.class})
@ConditionalOnProperty(prefix = MyBatisCustomProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(MyBatisCustomProperties.class)
public class MyBatisCustomAutoConfiguration {

    private final CoreProperties coreProperties;

    public MyBatisCustomAutoConfiguration(CoreProperties coreProperties) {
        log.info("MyBatisCustomAutoConfiguration is enabled");
        log.info("MyBatisCustomAutoConfiguration multi-tenancy {}", coreProperties.getTenancy().isEnabled() ? "enabled" : "disabled");

        this.coreProperties = coreProperties;
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

    @Bean("mpMetaObjectHandler")
    @ConditionalOnMissingBean
    public MetaObjectHandler mpMetaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

    @Bean("mpTenantLineHandler")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = CoreProperties.TENANCY_PREFIX, name = "enabled", havingValue = "true")
    public TenantLineHandler mpTenantLineHandler() {
        List<String> tables = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(this.coreProperties.getTenancy().getExcludes())) {
            tables.addAll(this.coreProperties.getTenancy().getExcludes());
        }
        return new CustomTenantLineHandler(tables);
    }

    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor(@Autowired(required = false) TenantLineHandler mpTenantLineHandler) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户插件，需要放在第一位
        if (this.coreProperties.getTenancy().isEnabled()) {
            TenantLineInnerInterceptor tenantInterceptor = new TenantLineInnerInterceptor();
            tenantInterceptor.setTenantLineHandler(mpTenantLineHandler);
            interceptor.addInnerInterceptor(tenantInterceptor);
        }
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
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
