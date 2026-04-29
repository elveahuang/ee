package cc.wdev.platform.commons.web.servlet.filter;

import cc.wdev.platform.commons.core.tenant.TenantContext;
import cc.wdev.platform.commons.utils.mdc.MdcContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author elvea
 */
@Slf4j
public class ServletWebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        log.info("[ServletWebFilter] doFilter Start");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        long startTime, endTime, totalTime = 0;
        try {
            // 初始化MDC上下文
            log.info("[ServletWebFilter] Init MDC Context");
            MdcContext.handleServletRequest(request, response);
            log.info("[ReactiveWebFilter] MDC Context {}. URL [{}]", MdcContext.getRequestId(), request.getRequestURI());

            // 初始化租户上下文
            log.info("[ServletWebFilter] Init Tenant Context [{}]", request.getServerName());
            TenantContext.handleServletRequest(request, response);

            // 记录执行时间
            startTime = System.currentTimeMillis();
            chain.doFilter(request, response);
            endTime = System.currentTimeMillis();
            totalTime = endTime - startTime;
        } finally {
            log.info("[ServletWebFilter] doFilter end. URL [{}]. totalTime: [{}]", request.getRequestURI(), totalTime);

            log.info("[ServletWebFilter] Clear Tenant Context");
            TenantContext.clear();

            log.info("[ServletWebFilter] Clear MDC Context");
            MdcContext.clear();
        }
    }

}
