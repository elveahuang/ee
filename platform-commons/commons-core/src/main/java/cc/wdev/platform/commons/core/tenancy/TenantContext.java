package cc.wdev.platform.commons.core.tenancy;

/**
 * @author elvea
 */
public class TenantContext {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void handleRequest(TenantResolver tenantResolver) {
        Tenant tenant = tenantResolver.resolveTenant();
        setTenantId(tenant.getId());
    }

    public static void setTenantId(Long tenantId) {
        threadLocal.set(tenantId);
    }

    public static Long getTenantId() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }

}
