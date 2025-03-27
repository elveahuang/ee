package cc.elvea.platform.commons.autoconfigure.oapis.weixin;

import cc.elvea.platform.commons.autoconfigure.oapis.weixin.properties.WeiXinMaProperties;
import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.oapis.weixin.config.AppMaConfig;
import cc.elvea.platform.commons.oapis.weixin.service.WeiXinMaService;
import cc.elvea.platform.commons.oapis.weixin.service.impl.WeiXinMaServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@EnableConfigurationProperties({WeiXinMaProperties.class})
@ConditionalOnProperty(prefix = WeiXinMaProperties.PREFIX, name = "enabled", havingValue = "true")
public class WeiXinMaAutoConfiguration {

    public WeiXinMaAutoConfiguration() {
        log.info("WeiXinMaAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean(WeiXinMaService.class)
    public WeiXinMaService weiXinMaService(WeiXinMaProperties properties, CacheService cacheService) {
        // 默认微信小程序应用配置信息
        AppMaConfig appConfig = AppMaConfig.builder()
                .appId(properties.getAppId())
                .appSecret(properties.getAppSecret())
                .build();
        // 创建微信小程序服务实例
        WeiXinMaServiceImpl service = new WeiXinMaServiceImpl(cacheService, properties.getCacheKeyPrefix());
        service.setAppConfig(appConfig);
        return service;
    }

}
