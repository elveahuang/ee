package cc.elvea.platform.commons.autoconfigure.data;

import cc.elvea.platform.commons.autoconfigure.data.properties.MyBatisCustomProperties;
import cc.elvea.platform.commons.data.mybatis.handler.CustomMetaObjectHandler;
import cc.elvea.platform.commons.data.mybatis.id.CustomIdentifierGenerator;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MyBatisCustomProperties.class)
@ConditionalOnClass({MybatisPlusPropertiesCustomizer.class})
@ConditionalOnProperty(prefix = MyBatisCustomProperties.PREFIX, name = "enabled", havingValue = "true")
public class MyBatisCustomAutoConfiguration {

    public MyBatisCustomAutoConfiguration() {
        log.info("CustomMyBatisAutoConfiguration is enabled.");
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

    /**
     * 拦截器
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

    @Bean
    @ConditionalOnMissingBean
    public CustomMetaObjectHandler customMetaObjectHandler() {
        return new CustomMetaObjectHandler();
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
