package cc.elvea.platform.commons.autoconfigure.data;

import cc.elvea.platform.commons.autoconfigure.data.properties.ElasticsearchCustomProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ElasticsearchCustomProperties.class)
@ConditionalOnClass({ElasticsearchDataAutoConfiguration.class})
@ConditionalOnProperty(prefix = ElasticsearchCustomProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableElasticsearchAuditing
@EnableElasticsearchRepositories
public class ElasticsearchCustomAutoConfiguration {

    public ElasticsearchCustomAutoConfiguration() {
        log.info("ElasticsearchCustomAutoConfiguration is enabled.");
    }

}
