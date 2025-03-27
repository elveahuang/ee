package cc.elvea.platform.commons.autoconfigure.data;

import cc.elvea.platform.commons.autoconfigure.data.properties.MongoCustomProperties;
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
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MongoCustomProperties.class)
@ConditionalOnClass({MongoAutoConfiguration.class})
@ConditionalOnProperty(prefix = MongoCustomProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableMongoAuditing
@EnableMongoRepositories
public class MongoCustomAutoConfiguration {

    public MongoCustomAutoConfiguration() {
        log.info("CustomMongoAutoConfiguration is enabled.");
    }

}
