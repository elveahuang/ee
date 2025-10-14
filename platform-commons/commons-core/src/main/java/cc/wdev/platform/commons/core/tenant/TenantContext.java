package cc.wdev.platform.commons.core.tenant;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;

/**
 * @author elvea
 */
@Slf4j
public class TenantContext {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void handleServletRequest(ServletRequest request) {
        log.info("[TenantContext] handleServletRequest start");
        Tenant tenant = GlobalTenantManager.getResolver().resolveTenant((HttpServletRequest) request);
        setTenantId(tenant.getId());
        log.info("[TenantContext] handleServletRequest end. tenant [{}]", tenant.getId());
    }

    public static void handleReactiveRequest(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        log.info("[TenantContext] handleReactiveRequest start");
    }

    public static void setTenantId(Long tenantId) {
        threadLocal.set(tenantId);
    }

    public static Long getTenantId() {
        if (threadLocal.get() == null) {
            return GlobalTenantManager.getStore().root().getId();
        }
        return threadLocal.get();
    }

    public static void clear() {
        log.info("[TenantContext] clear context");
        threadLocal.remove();
    }

}
