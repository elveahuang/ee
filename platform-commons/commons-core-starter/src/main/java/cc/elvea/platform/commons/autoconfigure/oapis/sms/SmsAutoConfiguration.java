package cc.elvea.platform.commons.autoconfigure.oapis.sms;

import cc.elvea.platform.commons.autoconfigure.oapis.sms.properties.SmsProperties;
import cc.elvea.platform.commons.oapis.sms.SmsConfig;
import cc.elvea.platform.commons.oapis.sms.SmsFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = SmsProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({SmsProperties.class})
public class SmsAutoConfiguration {

    public SmsAutoConfiguration(SmsProperties properties) {
        log.info("SmsAutoConfiguration is enabled.");
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
