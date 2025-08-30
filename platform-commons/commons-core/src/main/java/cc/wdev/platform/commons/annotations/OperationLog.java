package cc.wdev.platform.commons.annotations;

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

    String module() default "";

    String value() default "";

    boolean request() default true;

    boolean response() default true;

}
