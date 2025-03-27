package cc.elvea.platform.commons.autoconfigure.oapis.dingtalk;

import cc.elvea.platform.commons.autoconfigure.oapis.dingtalk.properties.DingTalkProperties;
import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.oapis.dingtalk.cache.Cache;
import cc.elvea.platform.commons.oapis.dingtalk.cache.DefaultCache;
import cc.elvea.platform.commons.oapis.dingtalk.config.AppConfig;
import cc.elvea.platform.commons.oapis.dingtalk.service.DingTalkService;
import cc.elvea.platform.commons.oapis.dingtalk.service.impl.DingTalkServiceImpl;
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
@EnableConfigurationProperties({DingTalkProperties.class})
@ConditionalOnProperty(prefix = DingTalkProperties.PREFIX, name = "enabled", havingValue = "true")
public class DingTalkAutoConfiguration {

    public DingTalkAutoConfiguration() {
        log.info("DingTalkAutoConfiguration is enabled.");
    }

    @Bean("dingtalkCache")
    @ConditionalOnMissingBean(Cache.class)
    public Cache larkCache(CacheService cacheService, DingTalkProperties properties) {
        return new DefaultCache(cacheService, properties.getCacheKeyPrefix());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(Cache.class)
    public DingTalkService dingTalkService(Cache cache, DingTalkProperties properties) {
        AppConfig appConfig = AppConfig.builder()
                .corpId(properties.getCorpId())
                .agentId(properties.getAgentId())
                .appKey(properties.getAppKey())
                .appSecret(properties.getAppSecret())
                .build();
        DingTalkServiceImpl service = new DingTalkServiceImpl(cache, properties.getCacheKeyPrefix());
        service.setAppConfig(appConfig);
        return service;
    }

}
