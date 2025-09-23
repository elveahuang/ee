package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.LogProperties;
import cc.wdev.platform.commons.autoconfigure.web.properties.WebProperties;
import cc.wdev.platform.commons.core.log.aspect.OperationLogAspect;
import cc.wdev.platform.commons.core.log.domain.OperationLogDto;
import cc.wdev.platform.commons.core.log.domain.UrlLogDto;
import cc.wdev.platform.commons.core.log.store.DefaultLogStore;
import cc.wdev.platform.commons.core.log.store.LogStore;
import cc.wdev.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.wdev.platform.commons.web.interceptor.LogInterceptor;
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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.lang.NonNull;

import java.util.Set;

/**
 * @author elvea
 */
@Slf4j
@EnableAspectJAutoProxy
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
    public OperationLogAspect operationLogAspect(LogStore logStore) {
        log.info("Creating OperationLogAspect");
        return new OperationLogAspect(logStore);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public LogInterceptor logInterceptor(LogStore logStore) {
        log.info("Creating LogInterceptor");
        return new LogInterceptor(logStore);
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
