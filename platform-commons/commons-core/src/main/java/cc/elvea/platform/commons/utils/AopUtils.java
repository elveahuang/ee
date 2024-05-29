package cc.elvea.platform.commons.utils;

import org.aspectj.lang.JoinPoint;

/**
 * @author elvea
 * @since 24.1.0
 */
public abstract class AopUtils {

    public static String getJoinPointClass(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    public static String getJoinPointMethod(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

}
