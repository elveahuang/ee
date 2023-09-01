package cn.elvea.platform.commons.core.service;

import cn.elvea.platform.commons.core.utils.GenericsUtils;
import cn.elvea.platform.commons.core.utils.SpringUtils;

/**
 * 用于在实现类中从Spring上下文获取自身实例
 * <p>
 * 因为Spring AOP的限制，类内部方法相互调用的时候，会使得缓存等不走代理导致缓存失效，
 * 所以获取自身代理对象后，就不受限制。
 *
 * @param <T> 服务类
 * @author elvea
 * @since 0.0.1
 */
public interface ProxySelfService<T> {

    /**
     * 默认获取自身代理实例的方法
     *
     * @return 自身代理实例/自身实例
     */
    default T getInstance() {
        try {
            return SpringUtils.getBean(this.getServiceClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前服务类
     *
     * @return Class<T>
     */
    default Class<T> getServiceClass() {
        return GenericsUtils.getSuperGenericType(getClass(), ProxySelfService.class, 0);
    }

    /**
     * 默认获取自身代理实例的方法
     *
     * @param clazz    代理类
     * @param instance 代理类实例
     * @return 自身代理实例/自身实例
     */
    default T getInstance(Class<T> clazz, T instance) {
        try {
            return SpringUtils.getBean(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

}
