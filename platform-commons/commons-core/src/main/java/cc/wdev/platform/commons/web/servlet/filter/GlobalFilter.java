package cc.wdev.platform.commons.web.servlet.filter;

import cc.wdev.platform.commons.core.tenant.TenantContext;
import cc.wdev.platform.commons.security.auth.AuthContext;
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
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        log.info("[GlobalFilter] doFilter Start");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        long startTime, endTime, totalTime = 0;
        try {
            // 初始化MDC上下文
            log.info("[GlobalFilter] Init MDC Context");
            MdcContext.handleServletRequest(request, response);

            // 初始化租户上下文
            log.info("[GlobalFilter] Init Tenant Context");
            TenantContext.handleServletRequest(request);

            // 初始化认证上下文
            log.info("[GlobalFilter] Init Auth Context");
            AuthContext.handleServletRequest(request);

            // 记录执行时间
            startTime = System.currentTimeMillis();
            chain.doFilter(request, response);
            endTime = System.currentTimeMillis();
            totalTime = endTime - startTime;
        } finally {
            log.info("[GlobalFilter] URL [{}]. totalTime: [{}]", request.getRequestURI(), totalTime);

            log.info("[GlobalFilter] Clear Tenant Context");
            TenantContext.clear();

            log.info("[GlobalFilter] Clear MDC Context");
            TenantContext.clear();

            log.info("[GlobalFilter] Clear Auth Context");
            AuthContext.clear();

            log.info("[GlobalFilter] doFilter end");
        }
    }

}
