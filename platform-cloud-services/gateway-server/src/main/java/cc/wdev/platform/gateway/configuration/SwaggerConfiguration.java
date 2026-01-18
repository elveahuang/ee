package cc.wdev.platform.gateway.configuration;

import cc.wdev.platform.commons.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class SwaggerConfiguration {

    @Bean
    @Lazy(false)
    public Set<SwaggerUrl> apis(RouteDefinitionLocator locator, SwaggerUiConfigProperties swaggerUiConfigProperties) {
        Set<SwaggerUrl> urls = new HashSet<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        if (CollectionUtils.isNotEmpty(definitions)) {
            definitions.stream().filter(definition -> definition.getId().matches(".*-service")).forEach(definition -> {
                String name = definition.getId().replaceAll("-service", "");
                SwaggerUrl swaggerUrl = new SwaggerUrl(name, DEFAULT_API_DOCS_URL + "/" + definition.getId(), definition.getId());
                urls.add(swaggerUrl);
            });
        }
        swaggerUiConfigProperties.setUrls(urls);
        return urls;
    }

}
