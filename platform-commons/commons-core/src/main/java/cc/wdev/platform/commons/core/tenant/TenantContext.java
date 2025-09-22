package cc.wdev.platform.commons.core.tenant;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
 */
public class TenantContext {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void handleServletRequest(TenantResolver tenantResolver, ServletRequest request) {
        Tenant tenant = tenantResolver.resolveTenant((HttpServletRequest) request);
        setTenantId(tenant.getId());
    }

    public static void setTenantId(Long tenantId) {
        threadLocal.set(tenantId);
    }

    public static Long getTenantId() {
        if (threadLocal.get() == null) {
            return Tenant.defaultTenant.getId();
        }
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }

}
