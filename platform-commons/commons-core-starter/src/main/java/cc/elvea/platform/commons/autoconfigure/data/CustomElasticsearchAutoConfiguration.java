package cc.elvea.platform.commons.autoconfigure.data;

import cc.elvea.platform.commons.autoconfigure.data.properties.CustomElasticsearchProperties;
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
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ElasticsearchDataAutoConfiguration.class})
@ConditionalOnProperty(prefix = CustomElasticsearchProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(CustomElasticsearchProperties.class)
@EnableElasticsearchRepositories
@EnableElasticsearchAuditing
public class CustomElasticsearchAutoConfiguration {

    public CustomElasticsearchAutoConfiguration() {
        log.info("CustomElasticsearchAutoConfiguration is enabled.");
    }

}
