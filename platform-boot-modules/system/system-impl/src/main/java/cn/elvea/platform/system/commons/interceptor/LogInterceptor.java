package cn.elvea.platform.system.commons.interceptor;

import cn.elvea.platform.commons.core.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private Long startTime;

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
        Long endTime = System.currentTimeMillis();
        Long execTime = endTime - this.startTime;
        log.info("URL - [{}] - [{}] - [{}]", request.getRequestURI(), execTime, ServletUtils.getHost());
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) {
    }

}
