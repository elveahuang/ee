package cc.wdev.platform.commons.web.filter;

import cc.wdev.platform.commons.utils.mdc.MdcContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * @author elvea
 */
public class TraceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        try {
            MdcContext.handleServletRequest((HttpServletRequest) request, (HttpServletResponse) response);
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

}
