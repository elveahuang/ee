package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.CaptchaProperties;
import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.extensions.captcha.provider.*;
import cc.elvea.platform.commons.extensions.captcha.service.CaptchaService;
import cc.elvea.platform.commons.extensions.captcha.service.DefaultCaptchaService;
import cc.elvea.platform.commons.extensions.captcha.store.CaptchaStore;
import cc.elvea.platform.commons.extensions.captcha.store.DefaultCaptchaStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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
@EnableConfigurationProperties({CaptchaProperties.class})
@ConditionalOnProperty(prefix = CaptchaProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class CaptchaAutoConfiguration {

    public CaptchaAutoConfiguration() {
        log.info("CaptchaAutoConfiguration is enabled.");
    }

    @Bean("mailCaptchaProvider")
    @ConditionalOnMissingBean()
    public MailCaptchaProvider mailCaptchaProvider() {
        return new DefaultMailCaptchaProvider();
    }

    @Bean("smsCaptchaProvider")
    @ConditionalOnMissingBean()
    public SmsCaptchaProvider smsCaptchaProvider() {
        return new DefaultSmsCaptchaProvider();
    }

    @Bean("codeCaptchaProvider")
    @ConditionalOnMissingBean()
    public CodeCaptchaProvider codeCaptchaProvider() {
        return new DefaultCodeCaptchaProvider();
    }

    @Bean("captchaStore")
    @ConditionalOnMissingBean(CaptchaStore.class)
    public CaptchaStore captchaStore(@Qualifier("cacheService") CacheService cacheService,
                                     CaptchaProperties properties) {
        return new DefaultCaptchaStore(cacheService, properties.getCacheKeyPrefix());
    }

    @Bean
    @ConditionalOnMissingBean
    public CaptchaService captchaService(@Qualifier("captchaStore") CaptchaStore captchaStore,
                                         @Qualifier("mailCaptchaProvider") MailCaptchaProvider mailCaptchaProvider,
                                         @Qualifier("smsCaptchaProvider") SmsCaptchaProvider smsCaptchaProvider,
                                         @Qualifier("codeCaptchaProvider") CodeCaptchaProvider codeCaptchaProvider) {
        DefaultCaptchaService captchaService = new DefaultCaptchaService();
        captchaService.setCaptchaStore(captchaStore);
        captchaService.setCodeCaptchaProvider(codeCaptchaProvider);
        captchaService.setMailCaptchaProvider(mailCaptchaProvider);
        captchaService.setSmsCaptchaProvider(smsCaptchaProvider);
        return captchaService;
    }

}
