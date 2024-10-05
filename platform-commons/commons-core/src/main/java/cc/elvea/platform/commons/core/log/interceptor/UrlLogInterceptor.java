package cc.elvea.platform.commons.core.log.interceptor;


import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.core.log.store.LogStore;
import cc.elvea.platform.commons.utils.ServletUtils;
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
public class UrlLogInterceptor implements HandlerInterceptor {

    private final LogStore logStore;

    private Long startTime;

    private Long endTime;

    public UrlLogInterceptor(LogStore logStore) {
        this.logStore = logStore;
    }

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
            UrlLogDto dto = new UrlLogDto();
            dto.setPath(request.getRequestURI());
            dto.setStartTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(this.startTime), ZoneOffset.systemDefault()));
            dto.setEndTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(this.endTime), ZoneOffset.systemDefault()));
            dto.setExecTime(execTime);
            logStore.saveUrlLog(dto);
        } catch (Exception e) {
            log.error("Fail to save url - [{}] log.", request.getRequestURI(), e);
        }
    }

}
