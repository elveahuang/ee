package cc.elvea.platform.system.commons.interceptor;

import cc.elvea.platform.commons.logging.domain.UrlLogDto;
import cc.elvea.platform.commons.utils.ServletUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.log.service.UrlLogAmqpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private Long startTime;

    private Long endTime;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,
                           @NonNull HttpServletResponse response,
                           @NonNull Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                Exception ex) {
        endTime = System.currentTimeMillis();

        Long execTime = this.endTime - this.startTime;

        log.info("URL - [{}] - [{}] - [{}]", request.getRequestURI(), execTime, ServletUtils.getHost());
        try {
            UrlLogDto urlLog = new UrlLogDto();
            urlLog.setPath(request.getRequestURI());
            urlLog.setStartTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(this.startTime), ZoneOffset.systemDefault()));
            urlLog.setEndTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(this.endTime), ZoneOffset.systemDefault()));
            urlLog.setExecTime(execTime);
            SpringUtils.getBean(UrlLogAmqpService.class).send(urlLog);
        } catch (Exception e) {
            log.error("Fail to save url - [{}] log.", request.getRequestURI(), e);
        }
    }

}
