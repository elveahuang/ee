package cn.elvea.platform.commons.core.annotations;

import java.lang.annotation.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RateLimit {

    /**
     * 限流周期，单位秒钟
     */
    int period() default 60;

    /**
     * 当前IP的资源限流量
     */
    int limit() default 1;

}
