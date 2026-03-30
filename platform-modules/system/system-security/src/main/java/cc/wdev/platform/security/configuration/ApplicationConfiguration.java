package cc.wdev.platform.security.configuration;

import cc.wdev.platform.commons.constants.GlobalConstants;
import cc.wdev.platform.system.commons.constants.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author elvea
 */
@Slf4j
@EnableAspectJAutoProxy
@Configuration(proxyBeanMethods = false)
public class ApplicationConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
            SystemConstants.I18N_SECURITY_MESSAGES,
            SystemConstants.I18N_LABEL_MESSAGES,
            SystemConstants.I18N_VALIDATION_MESSAGES
        );
        messageSource.setDefaultEncoding(GlobalConstants.ENCODING);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

}
