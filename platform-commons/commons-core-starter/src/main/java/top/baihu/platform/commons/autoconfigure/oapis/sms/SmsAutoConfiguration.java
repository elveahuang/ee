package top.baihu.platform.commons.autoconfigure.oapis.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.autoconfigure.oapis.sms.properties.SmsProperties;
import top.baihu.platform.commons.oapis.sms.SmsConfig;
import top.baihu.platform.commons.oapis.sms.SmsFactory;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = SmsProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({SmsProperties.class})
public class SmsAutoConfiguration {

    public SmsAutoConfiguration(SmsProperties properties) {
        log.info("SmsAutoConfiguration is enabled");
        log.info("Current SMS type is {}", properties.getType());
    }

    @Bean
    public SmsFactory smsFactory(SmsProperties properties) {
        SmsConfig config = SmsConfig.builder()
            .type(properties.getType())
            .aliyun(properties.getAliyun())
            .build();
        return new SmsFactory(config);
    }

}
