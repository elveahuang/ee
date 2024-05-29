package cc.elvea.platform.commons.autoconfigure.oapis.facebody;

import cc.elvea.platform.commons.autoconfigure.oapis.facebody.properties.FaceBodyProperties;
import cc.elvea.platform.commons.oapis.facebody.FaceBodyConfig;
import cc.elvea.platform.commons.oapis.facebody.FaceBodyManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = FaceBodyProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({FaceBodyProperties.class})
public class FaceBodyAutoConfiguration {

    public FaceBodyAutoConfiguration(FaceBodyProperties properties) {
        log.info("Current default face-body service is {}", properties.getType());

    }

    @Bean
    @ConditionalOnMissingBean
    public FaceBodyManager faceBodyManager(FaceBodyProperties properties) {
        FaceBodyConfig config = FaceBodyConfig.builder()
                .enabled(properties.isEnabled())
                .type(properties.getType())
                .aliyun(properties.getAliyun())
                .tencent(properties.getTencent()).build();
        return new FaceBodyManager(config);
    }

}
