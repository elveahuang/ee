package cn.elvea.platform.commons.core.autoconfigure.sequence;

import cn.elvea.platform.commons.core.autoconfigure.sequence.properties.SequenceProperties;
import cn.elvea.platform.commons.core.sequence.Sequence;
import cn.elvea.platform.commons.core.sequence.SequenceManager;
import cn.elvea.platform.commons.core.sequence.snowflake.CosIdSnowflakeSequence;
import cn.elvea.platform.commons.core.sequence.snowflake.DefaultSnowflakeSequence;
import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.provider.IdGeneratorProvider;
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
@EnableConfigurationProperties({SequenceProperties.class})
@ConditionalOnProperty(prefix = SequenceProperties.PREFIX, name = "enabled", havingValue = "true")
public class SequenceAutoConfiguration {

    public SequenceAutoConfiguration() {
        log.info("SequenceAutoConfiguration is enabled.");
    }

    @Configuration(proxyBeanMethods = false)
    @EnableConfigurationProperties(SequenceProperties.class)
    @ConditionalOnProperty(name = "platform.sequence.type", havingValue = "auto")
    static class CosIdSequenceAutoConfiguration {

        public CosIdSequenceAutoConfiguration() {
            log.info("CosIdSequenceAutoConfiguration is enabled.");
        }

        @Bean
        @ConditionalOnMissingBean
        public Sequence sequence(IdGeneratorProvider provider) {
            Sequence sequence = new CosIdSnowflakeSequence(provider);
            SequenceManager.setSequence(sequence);
            return sequence;
        }

    }

    @Configuration(proxyBeanMethods = false)
    @EnableConfigurationProperties(SequenceProperties.class)
    @ConditionalOnProperty(name = "platform.sequence.type", havingValue = "manual", matchIfMissing = true)
    static class DefaultSequenceAutoConfiguration {

        public DefaultSequenceAutoConfiguration() {
            log.info("DefaultSequenceAutoConfiguration is enabled.");
        }

        @Bean
        @ConditionalOnMissingBean
        public Sequence sequence(SequenceProperties properties) {
            Sequence sequence = new DefaultSnowflakeSequence(
                    properties.getEpoch(),
                    properties.getDatacenterId(),
                    properties.getWorkerId()
            );
            SequenceManager.setSequence(sequence);
            return sequence;
        }

    }

}
