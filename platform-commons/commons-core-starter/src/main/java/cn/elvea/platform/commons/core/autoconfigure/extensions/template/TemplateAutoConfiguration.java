package cn.elvea.platform.commons.core.autoconfigure.extensions.template;

import cn.elvea.platform.commons.core.autoconfigure.extensions.template.properties.TemplateProperties;
import cn.elvea.platform.commons.core.extensions.template.DefaultHtmlTemplateService;
import cn.elvea.platform.commons.core.extensions.template.HtmlTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = TemplateProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({TemplateProperties.class})
public class TemplateAutoConfiguration {

    public TemplateAutoConfiguration() {
        log.info("CaptchaAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean()
    public HtmlTemplateService htmlTemplateService() {
        TemplateEngine engine = new TemplateEngine();
        return new DefaultHtmlTemplateService(engine);
    }

}
