package cn.elvea.platform.system.config;

import cn.elvea.platform.commons.core.constants.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Config
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {
        "cn.elvea.platform.system",
})
@MapperScan(basePackages = {
        "cn.elvea.platform.system.**.repository",
})
public class ApplicationConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:cn/elvea/platform/commons/messages/label",
                "classpath:cn/elvea/platform/commons/messages/validation",
                "classpath:org/springframework/security/messages",
                "classpath:messages/messages"
        );
        messageSource.setDefaultEncoding(GlobalConstants.ENCODING);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

}
