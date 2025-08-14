package top.baihu.platform.commons.autoconfigure.core;

import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.provider.IdGeneratorProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.autoconfigure.core.properties.SequenceProperties;
import top.baihu.platform.commons.core.sequence.Sequence;
import top.baihu.platform.commons.core.sequence.SequenceManager;
import top.baihu.platform.commons.core.sequence.snowflake.CosIdSnowflakeSequence;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({SequenceProperties.class})
@ConditionalOnClass({IdGeneratorProvider.class})
@ConditionalOnProperty(prefix = SequenceProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class SequenceAutoConfiguration {

    public SequenceAutoConfiguration() {
        log.info("SequenceAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean
    public Sequence sequence(IdGeneratorProvider provider) {
        Sequence sequence = new CosIdSnowflakeSequence(provider);
        SequenceManager.setSequence(sequence);
        return sequence;
    }

}
