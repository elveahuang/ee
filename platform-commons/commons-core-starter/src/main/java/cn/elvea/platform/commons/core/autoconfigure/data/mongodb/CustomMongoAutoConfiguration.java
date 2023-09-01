package cn.elvea.platform.commons.core.autoconfigure.data.mongodb;

import cn.elvea.platform.commons.core.autoconfigure.data.mongodb.properties.CustomMongoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({MongoAutoConfiguration.class})
@ConditionalOnProperty(prefix = CustomMongoProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableMongoRepositories
@EnableMongoAuditing
@EnableConfigurationProperties(CustomMongoProperties.class)
public class CustomMongoAutoConfiguration {

    public CustomMongoAutoConfiguration() {
        log.info("CustomMongoAutoConfiguration is enabled.");
    }

}
