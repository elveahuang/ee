package cn.elvea.platform.commons.core.autoconfigure.data.mybatis;

import cn.elvea.platform.commons.core.autoconfigure.data.mybatis.properties.CustomMyBatisProperties;
import cn.elvea.platform.commons.core.data.mybatis.handler.CustomMetaObjectHandler;
import cn.elvea.platform.commons.core.data.mybatis.id.CustomIdentifierGenerator;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CustomMyBatisProperties.class)
@ConditionalOnClass({MybatisPlusPropertiesCustomizer.class})
@ConditionalOnProperty(prefix = CustomMyBatisProperties.PREFIX, name = "enabled", havingValue = "true")
public class CustomMyBatisAutoConfiguration {

    public CustomMyBatisAutoConfiguration() {
        log.info("CustomMyBatisAutoConfiguration is enabled.");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // MyBatis-Plus
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 主键生成采用自定义的雪花算法
     */
    @Bean("mybatisPlusIdentifierGenerator")
    @ConditionalOnMissingBean
    public IdentifierGenerator identifierGenerator() {
        return new CustomIdentifierGenerator();
    }

    /**
     * MyBatis Plus 拦截器
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        return interceptor;
    }

    /**
     * MyBatisPlus 自定义配置
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
        return configuration -> configuration.getGlobalConfig()
                .getDbConfig()
                .setLogicDeleteField("active")
                .setLogicDeleteValue("0")
                .setLogicNotDeleteValue("1");
    }

    /**
     * @see CustomMetaObjectHandler
     */
    @Bean
    @ConditionalOnMissingBean
    public CustomMetaObjectHandler customMetaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // MyBatis
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * MyBatis 自定义配置
     */
    @Bean
    @ConditionalOnMissingBean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }

}
