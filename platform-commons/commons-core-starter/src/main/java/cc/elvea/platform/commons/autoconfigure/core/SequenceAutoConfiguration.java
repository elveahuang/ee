package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.SequenceProperties;
import cc.elvea.platform.commons.core.sequence.Sequence;
import cc.elvea.platform.commons.core.sequence.SequenceManager;
import cc.elvea.platform.commons.core.sequence.snowflake.CosIdSnowflakeSequence;
import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.provider.IdGeneratorProvider;
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
@EnableConfigurationProperties({SequenceProperties.class})
@ConditionalOnProperty(prefix = SequenceProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class SequenceAutoConfiguration {

    public SequenceAutoConfiguration() {
        log.info("SequenceAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public Sequence sequence(IdGeneratorProvider provider) {
        Sequence sequence = new CosIdSnowflakeSequence(provider);
        SequenceManager.setSequence(sequence);
        return sequence;
    }

}
