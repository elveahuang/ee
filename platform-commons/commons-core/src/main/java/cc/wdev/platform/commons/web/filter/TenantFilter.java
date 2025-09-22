package cc.wdev.platform.commons.web.filter;

import cc.wdev.platform.commons.core.tenant.TenantContext;
import cc.wdev.platform.commons.core.tenant.TenantResolver;
import jakarta.servlet.*;

import java.io.IOException;

/**
 * @author elvea
 */
public record TenantFilter(TenantResolver tenantResolver) implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        try {
            TenantContext.handleServletRequest(tenantResolver, request);
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }

}
