package top.baihu.platform.commons.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import top.baihu.platform.commons.utils.MDCUtils;
import top.baihu.platform.commons.utils.StringUtils;

import java.io.IOException;

public class GlobalRequestFilter extends OncePerRequestFilter {

    private static final String KEY_REQUEST_ID = "x-request-id";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        try {
            String requestId = request.getHeader(KEY_REQUEST_ID);
            if (StringUtils.isEmpty(requestId)) {
                requestId = StringUtils.uuid();
            }
            MDCUtils.setRequestId(requestId);
            response.addHeader(KEY_REQUEST_ID, requestId);
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

}
