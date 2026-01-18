package cc.wdev.platform.commons.core.tenant;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

/**
 * @author elvea
 */
@Slf4j
public class TenantContext {

    private static final ThreadLocal<Long> tenantId = new ThreadLocal<>();

    private static final ThreadLocal<Integer> tenantRootInd = new ThreadLocal<>();

    public static void handleServletRequest(ServletRequest request) {
        log.info("[TenantContext] handleServletRequest start");
        Tenant tenant = GlobalTenantManager.getResolver().resolveTenant((HttpServletRequest) request);
        setTenantId(tenant.getId());
        setTenantRootInd(tenant.getRootInd());
        log.info("[TenantContext] handleServletRequest end. tenant [{}]", tenant.getId());
    }

    public static void handleReactiveRequest(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        log.info("[TenantContext] handleReactiveRequest start");
    }

    public static void setTenantId(Long tenantId) {
        TenantContext.tenantId.set(tenantId);
    }

    public static Long getTenantId() {
        if (tenantId.get() == null) {
            return GlobalTenantManager.getStore().root().getId();
        }
        return tenantId.get();
    }

    public static void setTenantRootInd(Integer tenantRootInd) {
        TenantContext.tenantRootInd.set(tenantRootInd);
    }

    public static Integer getTenantRootInd() {
        if (tenantRootInd.get() == null) {
            return GlobalTenantManager.getStore().root().getRootInd();
        }
        return tenantRootInd.get();
    }

    public static void clear() {
        log.info("[TenantContext] clear context");
        tenantId.remove();
        tenantRootInd.remove();
    }

}
