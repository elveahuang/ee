package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.LogProperties;
import cc.elvea.platform.commons.autoconfigure.web.properties.WebProperties;
import cc.elvea.platform.commons.core.log.aspect.OperationLogAspect;
import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.core.log.interceptor.UrlLogInterceptor;
import cc.elvea.platform.commons.core.log.store.DefaultLogStore;
import cc.elvea.platform.commons.core.log.store.LogStore;
import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
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
        log.info("LogAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public LogStore logStore() {
        return new DefaultLogStore();
    }

    @Bean
    @ConditionalOnMissingBean
    public OperationLogAspect optLogAspect(LogStore logStore) {
        log.info("Creating OperationLogAspect...");
        return new OperationLogAspect(logStore);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public UrlLogInterceptor urlLogInterceptor(LogStore logStore) {
        log.info("Creating UrlLogInterceptor...");
        return new UrlLogInterceptor(logStore);
    }

    public static class LogRuntimeHints implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            log.info("Register RuntimeHints by LogRuntimeHints.");

            hints.reflection().registerType(UrlLogDto.class, MemberCategory.values());
            hints.serialization().registerType(UrlLogDto.class);

            hints.reflection().registerType(OperationLogDto.class, MemberCategory.values());
            hints.serialization().registerType(OperationLogDto.class);

            hints.reflection().registerType(CaptchaLogDto.class, MemberCategory.values());
            hints.serialization().registerType(CaptchaLogDto.class);
        }
    }

}
