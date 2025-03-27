package cc.elvea.platform.commons.autoconfigure.oapis.lark;

import cc.elvea.platform.commons.autoconfigure.oapis.lark.properties.LarkProperties;
import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.oapis.lark.cache.Cache;
import cc.elvea.platform.commons.oapis.lark.cache.RedisCache;
import cc.elvea.platform.commons.oapis.lark.config.AppConfig;
import cc.elvea.platform.commons.oapis.lark.service.LarkService;
import cc.elvea.platform.commons.oapis.lark.service.impl.LarkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
@EnableConfigurationProperties({LarkProperties.class})
@ConditionalOnProperty(prefix = LarkProperties.PREFIX, name = "enabled", havingValue = "true")
public class LarkAutoConfiguration {

    public LarkAutoConfiguration() {
        log.info("LarkAutoConfiguration is enabled.");
    }

    @Bean("larkCache")
    @ConditionalOnMissingBean(Cache.class)
    public Cache larkCache(CacheService cacheService, LarkProperties properties) {
        return new RedisCache(cacheService, properties.getCacheKeyPrefix());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(Cache.class)
    public LarkService larkService(Cache cache, LarkProperties properties) {
        AppConfig appConfig = AppConfig.builder()
                .appType(properties.getAppType())
                .appId(properties.getAppId())
                .appSecret(properties.getAppSecret())
                .verificationToken(properties.getVerificationToken())
                .encryptKey(properties.getEncryptKey())
                .debug(properties.isDebug())
                .cache(cache)
                .build();
        LarkServiceImpl service = new LarkServiceImpl(cache, properties.getCacheKeyPrefix());
        service.setAppConfig(appConfig);
        return service;
    }

}
