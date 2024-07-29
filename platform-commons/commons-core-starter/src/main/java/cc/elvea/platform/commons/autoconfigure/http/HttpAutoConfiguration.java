package cc.elvea.platform.commons.autoconfigure.http;

import cc.elvea.platform.commons.autoconfigure.http.properties.HttpProperties;
import cc.elvea.platform.commons.http.Http;
import cc.elvea.platform.commons.http.HttpManger;
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
@ConditionalOnProperty(prefix = HttpProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({HttpProperties.class})
public class HttpAutoConfiguration {

    public HttpAutoConfiguration() {
        log.info("HttpAutoConfiguration is enabled.");
    }

    /**
     * @return {@link HttpManger}
     */
    @Bean
    @ConditionalOnMissingBean
    public Http httpManger(HttpProperties properties) {
        Http http = Http.builder().debug(properties.getDebug()).proxy(properties.getProxy()).type(properties.getType()).build();
        HttpManger.setHttp(http);
        return http;
    }

}
