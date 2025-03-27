package cc.elvea.platform.commons.annotations;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author elvea
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    String value() default "";

}
