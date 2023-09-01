package cn.elvea.platform.commons.core.autoconfigure.oapis.lark;

import cn.elvea.platform.commons.core.autoconfigure.oapis.lark.properties.LarkProperties;
import cn.elvea.platform.commons.core.cache.service.CacheService;
import cn.elvea.platform.commons.core.oapis.lark.cache.Cache;
import cn.elvea.platform.commons.core.oapis.lark.cache.RedisCache;
import cn.elvea.platform.commons.core.oapis.lark.config.AppConfig;
import cn.elvea.platform.commons.core.oapis.lark.service.LarkService;
import cn.elvea.platform.commons.core.oapis.lark.service.impl.LarkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
                .debug(properties.getDebug())
                .cache(cache)
                .build();
        LarkServiceImpl service = new LarkServiceImpl(cache, properties.getCacheKeyPrefix());
        service.setAppConfig(appConfig);
        return service;
    }

}
