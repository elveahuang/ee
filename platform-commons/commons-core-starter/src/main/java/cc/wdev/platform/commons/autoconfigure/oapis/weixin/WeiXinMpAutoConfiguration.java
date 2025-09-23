package cc.wdev.platform.commons.autoconfigure.oapis.weixin;

import cc.wdev.platform.commons.autoconfigure.oapis.weixin.properties.WeiXinMpProperties;
import cc.wdev.platform.commons.core.cache.service.CacheService;
import cc.wdev.platform.commons.oapis.weixin.config.AppMpConfig;
import cc.wdev.platform.commons.oapis.weixin.service.WeiXinMpService;
import cc.wdev.platform.commons.oapis.weixin.service.impl.WeiXinMpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author elvea
 */
@Slf4j
@EnableConfigurationProperties({WeiXinMpProperties.class})
@ConditionalOnProperty(prefix = WeiXinMpProperties.PREFIX, name = "enabled", havingValue = "true")
public class WeiXinMpAutoConfiguration {

    public WeiXinMpAutoConfiguration() {
        log.info("WeiXinMpAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean(WeiXinMpService.class)
    public WeiXinMpService weiXinMpService(WeiXinMpProperties properties, CacheService cacheService) {
        // 默认微信公众号应用配置信息
        AppMpConfig appConfig = AppMpConfig.builder()
            .appId(properties.getAppId())
            .appSecret(properties.getAppSecret())
            .build();
        // 创建微信公众号服务实例
        WeiXinMpServiceImpl service = new WeiXinMpServiceImpl(cacheService, properties.getCacheKeyPrefix());
        service.setAppConfig(appConfig);
        return service;
    }

}
