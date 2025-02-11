package cc.elvea.platform.commons.core.log.aspect;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.store.LogStore;
import cc.elvea.platform.commons.utils.AopUtils;
import cc.elvea.platform.commons.utils.ExceptionUtils;
import cc.elvea.platform.commons.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StopWatch;

/**
 * @author elvea
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class OperationLogAspect {

    private final LogStore logStore;

    private static final ThreadLocal<StopWatch> threadLocal = new ThreadLocal<>();

    /**
     * 执行前
     */
    @Before(value = "@annotation(operationLog)")
    public void boBefore(JoinPoint joinPoint, OperationLog operationLog) {
        StopWatch stopWatch = new StopWatch();
        threadLocal.set(stopWatch);
        stopWatch.start();
    }

    /**
     * 执行成功并返回
     */
    @AfterReturning(pointcut = "@annotation(operationLog)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, OperationLog operationLog, Object result) {
        outputLog(joinPoint, operationLog, null, result);
    }

    /**
     * 执行失败捕捉异常
     */
    @AfterThrowing(value = "@annotation(operationLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, OperationLog operationLog, Exception e) {
        outputLog(joinPoint, operationLog, e, null);
    }

    private void outputLog(JoinPoint joinPoint, OperationLog operationLog, Throwable e, Object result) {
        try {
            StopWatch stopWatch = threadLocal.get();
            stopWatch.stop();

            HttpServletRequest request = ServletUtils.getRequest();

            OperationLogDto dto = OperationLogDto.builder()
                    .className(AopUtils.getJoinPointClass(joinPoint))
                    .methodName(AopUtils.getJoinPointMethod(joinPoint))
                    .requestUri(request.getRequestURI())
                    .httpMethod(request.getMethod())
                    .requestIp(ServletUtils.getHost(request))
                    .requestUa(ServletUtils.getUserAgent(request))
                    .requestParams(ServletUtils.getParameterAsJson(request))
                    .requestHeaderParams(ServletUtils.getHeaderAsJson(request))
                    .execTime(stopWatch.getTotalTimeMillis())
                    .exception(e != null ? ExceptionUtils.getStackTraceAsString(e) : "")
                    .details(operationLog.value())
                    .build();
            logStore.saveOperationLog(dto);
        } catch (Exception ex) {
            log.error("Failed to save operation log.", ex);
        }
    }

}
