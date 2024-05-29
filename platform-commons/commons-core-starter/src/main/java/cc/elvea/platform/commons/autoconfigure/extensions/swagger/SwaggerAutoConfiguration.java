package cc.elvea.platform.commons.autoconfigure.extensions.swagger;

import cc.elvea.platform.commons.autoconfigure.extensions.swagger.properties.SwaggerProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
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
@ConditionalOnProperty(name = "springdoc.api-docs.enabled", havingValue = "true")
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    private final SwaggerProperties properties;

    public SwaggerAutoConfiguration(SwaggerProperties properties) {
        this.properties = properties;
        log.info("SwaggerAutoConfiguration is enabled.");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes("basicScheme",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title(properties.getTitle())
                        .version(properties.getVersion())
                        .description(properties.getDescription()));
    }

}
