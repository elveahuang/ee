package cc.elvea.platform.commons.autoconfigure.oapis.facebody;

import cc.elvea.platform.commons.autoconfigure.oapis.facebody.properties.FaceBodyProperties;
import cc.elvea.platform.commons.oapis.facebody.FaceBodyConfig;
import cc.elvea.platform.commons.oapis.facebody.FaceBodyFactory;
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
@EnableConfigurationProperties({FaceBodyProperties.class})
@ConditionalOnProperty(prefix = FaceBodyProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class FaceBodyAutoConfiguration {

    public FaceBodyAutoConfiguration(FaceBodyProperties properties) {
        log.info("FaceBodyAutoConfiguration is enabled.");
        log.info("Current FaceBody type is {}", properties.getType());
    }

    @Bean
    @ConditionalOnMissingBean
    public FaceBodyFactory faceBodyFactory(FaceBodyProperties properties) {
        FaceBodyConfig config = FaceBodyConfig.builder()
                .enabled(properties.isEnabled())
                .type(properties.getType())
                .aliyun(properties.getAliyun())
                .tencent(properties.getTencent()).build();
        return new FaceBodyFactory(config);
    }

}
