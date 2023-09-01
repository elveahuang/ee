package cn.elvea.platform.commons.core.log.aspect;

import cn.elvea.platform.commons.core.log.LogManager;
import cn.elvea.platform.commons.core.log.dto.OperationLogDto;
import cn.elvea.platform.commons.core.utils.AopUtils;
import cn.elvea.platform.commons.core.utils.DateTimeUtils;
import cn.elvea.platform.commons.core.utils.ExceptionUtils;
import cn.elvea.platform.commons.core.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Aspect
public class OperationLogAspect extends AbstractLogAspect {

    public OperationLogAspect(LogManager logManager) {
        super(logManager);
    }

    @Pointcut("@annotation(cn.elvea.platform.commons.core.annotations.OperationLog)")
    protected void operationLogAspect() {
    }

    @Around("operationLogAspect()")
    protected Object doAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        output(joinPoint, startTime, endTime);
        return result;
    }

    @AfterThrowing(pointcut = "operationLogAspect()", throwing = "e")
    protected void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        output(joinPoint, e);
    }

    private void output(JoinPoint joinPoint, Throwable e) {
        try {
            HttpServletRequest request = ServletUtils.getRequest();

            OperationLogDto dto = OperationLogDto.builder()
                    .className(AopUtils.getJoinPointClass(joinPoint))
                    .methodName(AopUtils.getJoinPointMethod(joinPoint))
                    .requestUri(request.getRequestURI())
                    .httpMethod(request.getMethod())
                    .requestIp(ServletUtils.getHost())
                    .requestUa(ServletUtils.getUserAgent(request))
                    .requestParams(ServletUtils.getParamJson(request))
                    .requestHeaderParams(ServletUtils.getHeaderJson(request))
                    .execTime(0L)
                    .exception(e != null ? ExceptionUtils.getStackTraceAsString(e) : "")
                    .build();

            logManager.saveLog(dto);
        } catch (Exception ex) {
            log.error("Failed to save operation log.", ex);
        }
    }

    private void output(JoinPoint joinPoint, Long startTimeMillis, Long endTimeMillis) {
        try {
            HttpServletRequest request = ServletUtils.getRequest();

            OperationLogDto dto = OperationLogDto.builder()
                    .className(AopUtils.getJoinPointClass(joinPoint))
                    .methodName(AopUtils.getJoinPointMethod(joinPoint))
                    .requestUri(request.getRequestURI())
                    .httpMethod(request.getMethod())
                    .requestIp(ServletUtils.getHost())
                    .requestUa(ServletUtils.getUserAgent(request))
                    .requestParams(ServletUtils.getParamJson(request))
                    .requestHeaderParams(ServletUtils.getHeaderJson(request))
                    .startTime(DateTimeUtils.toLocalDateTime(startTimeMillis))
                    .endTime(DateTimeUtils.toLocalDateTime(endTimeMillis))
                    .execTime((endTimeMillis - startTimeMillis))
                    .build();

            logManager.saveLog(dto);
        } catch (Exception e) {
            log.error("Failed to save operation log.", e);
        }
    }

}
