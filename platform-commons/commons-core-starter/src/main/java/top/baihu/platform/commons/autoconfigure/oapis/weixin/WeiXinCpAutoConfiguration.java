package top.baihu.platform.commons.autoconfigure.oapis.weixin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.autoconfigure.oapis.weixin.properties.WeiXinCpProperties;
import top.baihu.platform.commons.core.cache.service.CacheService;
import top.baihu.platform.commons.oapis.weixin.config.AppCpConfig;
import top.baihu.platform.commons.oapis.weixin.service.WeiXinCpService;
import top.baihu.platform.commons.oapis.weixin.service.impl.WeiXinCpServiceImpl;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WeiXinCpProperties.class})
@ConditionalOnProperty(prefix = WeiXinCpProperties.PREFIX, name = "enabled", havingValue = "true")
public class WeiXinCpAutoConfiguration {

    public WeiXinCpAutoConfiguration() {
        log.info("WeiXinCpAutoConfiguration is enabled");
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
