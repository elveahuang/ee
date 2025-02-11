package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.HttpProperties;
import cc.elvea.platform.commons.extensions.http.HttpFactory;
import cc.elvea.platform.commons.extensions.http.HttpManger;
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
@EnableConfigurationProperties({HttpProperties.class})
@ConditionalOnProperty(prefix = HttpProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class HttpAutoConfiguration {

    public HttpAutoConfiguration() {
        log.info("HttpAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpFactory httpFactory(HttpProperties properties) {
        HttpFactory http = HttpFactory.builder()
                .debug(properties.getDebug())
                .proxy(properties.getProxy())
                .type(properties.getType())
                .build();
        HttpManger.setHttp(http);
        return http;
    }

}
