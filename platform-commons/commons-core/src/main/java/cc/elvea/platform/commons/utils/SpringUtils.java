package cc.elvea.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.util.function.Function;

/**
 * @author elvea
 * @since 24.1.0
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

    /**
     * 编程式事务
     */
    public static <T> T executeWithResult(Function<Object, T> function) {
        return executeWithResult(getBean(PlatformTransactionManager.class), function);
    }

    /**
     * 编程式事务
     */
    public static <T> T executeWithResult(PlatformTransactionManager transactionManager, Function<Object, T> function) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        return transactionTemplate.execute(status -> {
            try {
                return function.apply(new Object());
            } catch (Exception e) {
                status.setRollbackOnly();
                return null;
            }
        });
    }

    /**
     * 编程式事务
     */
    public static void executeWithoutResult(Function<Object, ?> function) {
        executeWithoutResult(getBean(PlatformTransactionManager.class), function);
    }

    /**
     * 编程式事务
     */
    public static void executeWithoutResult(PlatformTransactionManager transactionManager, Function<Object, ?> function) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.executeWithoutResult(status -> {
            try {
                function.apply(new Object());
            } catch (Exception e) {
                status.setRollbackOnly();
            }
        });
    }

}
