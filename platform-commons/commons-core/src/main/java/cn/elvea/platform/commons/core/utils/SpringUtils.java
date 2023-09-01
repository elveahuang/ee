package cn.elvea.platform.commons.core.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "applicationContext must not be null.");
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * 获取实例
     *
     * @param name 名称
     * @return Object
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 获取实例
     *
     * @param name         名称
     * @param requiredType 类型
     * @param <T>          T
     * @return T
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 获取实例
     *
     * @param clazz T
     * @param <T>   T
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 检查是否包含该类
     *
     * @param name String
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 检查是否是单例
     *
     * @param name String
     * @return boolean
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取类型
     *
     * @param name String
     * @return Class<?>
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }

}
