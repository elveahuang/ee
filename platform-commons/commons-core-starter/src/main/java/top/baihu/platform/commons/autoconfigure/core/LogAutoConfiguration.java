package top.baihu.platform.commons.autoconfigure.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.lang.NonNull;
import top.baihu.platform.commons.autoconfigure.core.properties.LogProperties;
import top.baihu.platform.commons.autoconfigure.web.properties.WebProperties;
import top.baihu.platform.commons.core.log.aspect.OperationLogAspect;
import top.baihu.platform.commons.core.log.domain.OperationLogDto;
import top.baihu.platform.commons.core.log.domain.UrlLogDto;
import top.baihu.platform.commons.core.log.interceptor.UrlLogInterceptor;
import top.baihu.platform.commons.core.log.store.DefaultLogStore;
import top.baihu.platform.commons.core.log.store.LogStore;
import top.baihu.platform.commons.extensions.captcha.domain.CaptchaLogDto;

import java.util.Set;

/**
 * @author elvea
 */
@Slf4j
@EnableAspectJAutoProxy
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(CoreAutoConfiguration.class)
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@ImportRuntimeHints(LogAutoConfiguration.LogRuntimeHints.class)
@RegisterReflectionForBinding({UrlLogDto.class, OperationLogDto.class, CaptchaLogDto.class})
public class LogAutoConfiguration {

    public LogAutoConfiguration() {
        log.info("LogAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean
    public LogStore logStore() {
        return new DefaultLogStore();
    }

    @Bean
    @ConditionalOnMissingBean
    public OperationLogAspect optLogAspect(LogStore logStore) {
        log.info("Creating OperationLogAspect");
        return new OperationLogAspect(logStore);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public UrlLogInterceptor urlLogInterceptor(LogStore logStore) {
        log.info("Creating UrlLogInterceptor");
        return new UrlLogInterceptor(logStore);
    }

    public static class LogRuntimeHints implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(@NonNull RuntimeHints hints, ClassLoader classLoader) {
            log.info("Register RuntimeHints by LogRuntimeHints");

            var mcs = MemberCategory.values();
            for (var type : Set.of(UrlLogDto.class, OperationLogDto.class, CaptchaLogDto.class)) {
                hints.reflection().registerType(type, mcs);
                hints.serialization().registerType(type);
            }
        }
    }

}
