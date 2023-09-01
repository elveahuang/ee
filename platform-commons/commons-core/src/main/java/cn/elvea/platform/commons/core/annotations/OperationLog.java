package cn.elvea.platform.commons.core.annotations;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author elvea
 * @since 0.0.1
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    String value() default "";

}
