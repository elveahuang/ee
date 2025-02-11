package cc.elvea.platform.commons.autoconfigure.web;

import cc.elvea.platform.commons.autoconfigure.web.properties.SwaggerProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = SwaggerProperties.PREFIX, name = "enabled", havingValue = "true")
public class SwaggerAutoConfiguration {

    public SwaggerAutoConfiguration() {
        log.info("SwaggerAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI openAPI(SwaggerProperties properties) {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
                )
                .info(
                        new Info().title(properties.getTitle())
                                .version(properties.getVersion())
                                .description(properties.getDescription())
                );
    }

}
