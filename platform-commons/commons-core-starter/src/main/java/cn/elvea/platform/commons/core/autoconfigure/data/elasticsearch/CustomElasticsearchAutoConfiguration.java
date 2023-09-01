package cn.elvea.platform.commons.core.autoconfigure.data.elasticsearch;

import cn.elvea.platform.commons.core.autoconfigure.data.elasticsearch.properties.CustomElasticsearchProperties;
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
 * @since 0.0.1
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
