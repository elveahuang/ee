package cn.elvea.platform.commons.core.extensions.limit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 限流拦截
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Aspect
public class RateLimitAspect {

    @Around("@annotation(cn.elvea.platform.commons.core.annotations.RateLimit)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }

}
