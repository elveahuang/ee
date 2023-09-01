package cn.elvea.platform.commons.core.utils;

import org.aspectj.lang.JoinPoint;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class AopUtils {

    public static String getJoinPointClass(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    public static String getJoinPointMethod(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

}
