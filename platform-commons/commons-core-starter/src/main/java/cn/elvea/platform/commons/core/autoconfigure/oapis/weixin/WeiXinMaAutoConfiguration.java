package cn.elvea.platform.commons.core.autoconfigure.oapis.weixin;

import cn.elvea.platform.commons.core.autoconfigure.oapis.weixin.properties.WeiXinMaProperties;
import cn.elvea.platform.commons.core.cache.service.CacheService;
import cn.elvea.platform.commons.core.oapis.weixin.config.AppMaConfig;
import cn.elvea.platform.commons.core.oapis.weixin.service.WeiXinMaService;
import cn.elvea.platform.commons.core.oapis.weixin.service.impl.WeiXinMaServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@EnableConfigurationProperties({WeiXinMaProperties.class})
@ConditionalOnProperty(prefix = WeiXinMaProperties.PREFIX, name = "enabled", havingValue = "true")
public class WeiXinMaAutoConfiguration {

    public WeiXinMaAutoConfiguration() {
        log.info("WeiXinMaAutoConfiguration is Enabled.");
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
