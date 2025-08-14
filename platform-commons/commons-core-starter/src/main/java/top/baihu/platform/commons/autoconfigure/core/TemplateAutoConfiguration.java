package top.baihu.platform.commons.autoconfigure.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import top.baihu.platform.commons.autoconfigure.core.properties.TemplateProperties;
import top.baihu.platform.commons.utils.template.DefaultHtmlTemplateService;
import top.baihu.platform.commons.utils.template.HtmlTemplateService;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({TemplateProperties.class})
@ConditionalOnClass({TemplateEngine.class})
@ConditionalOnProperty(prefix = TemplateProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class TemplateAutoConfiguration {

    public TemplateAutoConfiguration() {
        log.info("TemplateAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean()
    public HtmlTemplateService htmlTemplateService() {
        TemplateEngine engine = new TemplateEngine();
        return new DefaultHtmlTemplateService(engine);
    }

}
