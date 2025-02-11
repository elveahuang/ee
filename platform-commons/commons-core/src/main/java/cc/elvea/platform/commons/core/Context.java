package cc.elvea.platform.commons.core;

import cc.elvea.platform.commons.constants.GlobalConstants;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Locale;

/**
 * @author elvea
 */
@Builder
public class Context implements Serializable, ApplicationContextAware, MessageSourceAware, EnvironmentAware {

    @Setter
    @Getter
    @Builder.Default
    private String applicationName = GlobalConstants.NAME;

    @Setter
    @Getter
    @Builder.Default
    private String applicationVersion = GlobalConstants.VERSION;

    @Setter
    @Getter
    @Builder.Default
    private boolean debugEnabled = false;

    @Setter
    @Getter
    @Builder.Default
    private Context.Home home = Context.Home.builder().build();

    @Setter
    @Getter
    @Builder.Default
    private boolean amqpEnabled = false;

    private Environment environment;

    private MessageSource messageSource;

    private ApplicationContext applicationContext;

    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messageSource = messageSource;
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    /**
     * 获取配置项
     *
     * @param key 配置项
     * @return String
     */
    public String getProperty(String key) {
        return this.getProperty(key, "");
    }

    /**
     * 获取配置项
     *
     * @param key          配置项
     * @param defaultValue 默认值
     * @return String
     */
    public String getProperty(String key, String defaultValue) {
        return this.environment.getProperty(key, defaultValue);
    }

    /**
     * 获取配置项
     *
     * @param key   配置项
     * @param clazz 返回值类型
     * @return T
     */
    public <T> T getProperty(String key, Class<T> clazz) {
        return this.environment.getProperty(key, clazz);
    }

    /**
     * 获取配置项
     *
     * @param key          配置项
     * @param clazz        返回值类型
     * @param defaultValue 默认值
     * @return T
     */
    public <T> T getProperty(String key, Class<T> clazz, T defaultValue) {
        return this.environment.getProperty(key, clazz, defaultValue);
    }

    /**
     * 获取多语言文本
     */
    public String getMessage(String code, Locale locale) {
        return this.messageSourceAccessor.getMessage(code, locale);
    }

    /**
     * 获取多语言文本
     */
    public String getMessage(String code, String defaultMessage, Locale locale) {
        return this.messageSourceAccessor.getMessage(code, defaultMessage, locale);
    }

    /**
     * 获取多语言文本
     */
    public String getMessage(String code, Object[] args, Locale locale) {
        return this.messageSourceAccessor.getMessage(code, args, locale);
    }

    /**
     * 获取多语言文本
     */
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return this.messageSourceAccessor.getMessage(code, args, defaultMessage, locale);
    }

    @Data
    @Builder
    public static class Home implements Serializable {
        @Builder.Default
        private String main = "";
        @Builder.Default
        private String admin = "";
        @Builder.Default
        private String webapp = "";
        @Builder.Default
        private String mobile = "";
    }

}
