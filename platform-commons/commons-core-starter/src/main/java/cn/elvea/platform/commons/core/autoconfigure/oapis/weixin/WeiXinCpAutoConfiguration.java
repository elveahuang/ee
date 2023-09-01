package cn.elvea.platform.commons.core.autoconfigure.oapis.weixin;

import cn.elvea.platform.commons.core.autoconfigure.oapis.weixin.properties.WeiXinCpProperties;
import cn.elvea.platform.commons.core.cache.service.CacheService;
import cn.elvea.platform.commons.core.oapis.weixin.config.AppCpConfig;
import cn.elvea.platform.commons.core.oapis.weixin.service.WeiXinCpService;
import cn.elvea.platform.commons.core.oapis.weixin.service.impl.WeiXinCpServiceImpl;
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
@ConditionalOnProperty(prefix = WeiXinCpProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({WeiXinCpProperties.class})
public class WeiXinCpAutoConfiguration {

    public WeiXinCpAutoConfiguration() {
        log.info("WeiXinCpAutoConfiguration is Enabled.");
    }

    @Bean
    @ConditionalOnMissingBean(WeiXinCpService.class)
    public WeiXinCpService weiXinCpService(WeiXinCpProperties properties, CacheService cacheService) {
        // 默认企业微信应用配置信息
        AppCpConfig appConfig = AppCpConfig.builder()
                .corpId(properties.getCorpId())
                .corpSecret(properties.getCorpSecret())
                .agentId(properties.getAgentId())
                .token(properties.getToken())
                .build();
        // 创建企业微信服务实例
        WeiXinCpServiceImpl service = new WeiXinCpServiceImpl(cacheService, properties.getCacheKeyPrefix());
        service.setAppConfig(appConfig);
        return service;
    }

}
