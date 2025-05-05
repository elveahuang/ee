package cc.elvea.platform.commons.core.log.interceptor;

import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.core.log.store.LogStore;
import cc.elvea.platform.commons.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class UrlLogInterceptor implements HandlerInterceptor {

    private final LogStore logStore;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,
                           @NonNull HttpServletResponse response,
                           @NonNull Object handler,
                           ModelAndView modelAndView) {
        long endTime = System.currentTimeMillis();
        request.setAttribute("endTime", endTime);
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                Exception ex) {
        long startTime = (long) request.getAttribute("startTime");
        long endTime = request.getAttribute("endTime") == null ? System.currentTimeMillis() : (long) request.getAttribute("endTime");
        long execTime = endTime - startTime;
        log.info("URL - [{}] - [{}] - [{}]", request.getRequestURI(), execTime, ServletUtils.getHost());

        try {
            UrlLogDto dto = new UrlLogDto();
            dto.setPath(request.getRequestURI());
            dto.setStartTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneOffset.systemDefault()));
            dto.setEndTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), ZoneOffset.systemDefault()));
            dto.setExecTime(execTime);
            logStore.saveUrlLog(dto);
        } catch (Exception e) {
            log.error("Fail to save url - [{}] log.", request.getRequestURI(), e);
        }
    }

}
