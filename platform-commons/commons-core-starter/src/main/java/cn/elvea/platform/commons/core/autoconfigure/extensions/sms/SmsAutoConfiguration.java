package cn.elvea.platform.commons.core.autoconfigure.extensions.sms;

import cn.elvea.platform.commons.core.autoconfigure.extensions.sms.properties.SmsProperties;
import cn.elvea.platform.commons.core.extensions.sms.SmsSender;
import cn.elvea.platform.commons.core.extensions.sms.SmsServer;
import cn.elvea.platform.commons.core.extensions.sms.aliyun.AliyunSmsSender;
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
@ConditionalOnProperty(prefix = SmsProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({SmsProperties.class})
public class SmsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SmsSender smsSender(SmsProperties properties) {
        if (properties.getAliyun().getEnabled()) {
            return new AliyunSmsSender(SmsServer.builder().aliyun(properties.getAliyun()).build());
        }
        return new AliyunSmsSender();
    }

}
