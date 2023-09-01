package cn.elvea.platform.config;

import cn.elvea.platform.commons.core.constants.GlobalConstants;
import cn.elvea.platform.commons.core.extensions.captcha.store.CaptchaLogStore;
import cn.elvea.platform.commons.core.log.LogManagerCustomizer;
import cn.elvea.platform.system.commons.interceptor.LogInterceptor;
import cn.elvea.platform.system.log.api.LogApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@EnableAspectJAutoProxy
@Configuration(proxyBeanMethods = false)
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

    /**
     * 自定义验证码日志存储，用于保存验证码发送记录
     */
    @Bean("captchaLogStore")
    public CaptchaLogStore captchaLogStore(LogApi logApi) {
        return logApi::saveCaptchaLog;
    }

    /**
     * 自定义日志存储，用于保存操作记录
     */
    @Bean
    public LogManagerCustomizer logManagerCustomizer(LogApi logApi) {
        return configuration -> configuration.last(logApi::saveOperationLog);
    }

    /**
     * 创建日志拦截器
     */
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

}
