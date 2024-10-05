package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.TemplateProperties;
import cc.elvea.platform.commons.core.template.DefaultHtmlTemplateService;
import cc.elvea.platform.commons.core.template.HtmlTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = TemplateProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({TemplateProperties.class})
public class TemplateAutoConfiguration {

    public TemplateAutoConfiguration() {
        log.info("TemplateAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean()
    public HtmlTemplateService htmlTemplateService() {
        TemplateEngine engine = new TemplateEngine();
        return new DefaultHtmlTemplateService(engine);
    }

}
