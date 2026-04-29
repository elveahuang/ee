package cc.wdev.platform.commons.core.tenant;

import cc.wdev.platform.commons.utils.NumberUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import static cc.wdev.platform.commons.constants.GlobalConstants.TENANT_ID_KEY;
import static cc.wdev.platform.commons.constants.GlobalConstants.TENANT_ROOT_IND_KEY;

/**
 * @author elvea
 */
@Slf4j
public class TenantContext {

    private static final ThreadLocal<Long> tenantId = new ThreadLocal<>();

    private static final ThreadLocal<Integer> tenantRootInd = new ThreadLocal<>();

    public static void handleServletRequest(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        log.info("[TenantContext] handleServletRequest start");
        String tenantId = request.getHeader(TENANT_ID_KEY);
        String tenantRootInd = request.getHeader(TENANT_ROOT_IND_KEY);
        if (StringUtils.isEmpty(tenantId)
            || StringUtils.isBlank(tenantRootInd)) {
            Tenant tenant = GlobalTenantManager.getResolver().resolveTenant(request);
            tenantId = tenant.getIdAsString();
            setTenantId(tenant.getId());
            tenantRootInd = NumberUtils.toString(tenant.getRootInd());
            log.info("[TenantContext] handleServletRequest end. tenant [{}]", tenant.getId());
            log.info("[TenantContext] generate servlet tenantId [{}] for request [{}] [{}]", tenantId, request.getServerName(), request.getRequestURI());
        }
        setTenantId(NumberUtils.toLong(tenantId));
        setTenantRootInd(NumberUtils.toInteger(tenantRootInd));
        if (!response.getHeaderNames().contains(TENANT_ID_KEY)
            || !response.getHeaderNames().contains(TENANT_ROOT_IND_KEY)) {
            log.info("[TenantContext] set servlet tenantId [{}] for url [{}]", tenantId, request.getRequestURI());
            response.setHeader(TENANT_ID_KEY, tenantId);
            response.setHeader(TENANT_ROOT_IND_KEY, tenantRootInd);
        }
    }

    public static Mono<Void> handleReactiveRequest(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        log.info("[TenantContext] handleReactiveRequest start");
        String tenantId = request.getHeaders().getFirst(TENANT_ID_KEY);

        if (StringUtils.isEmpty(tenantId)) {
            // 异步获取并设置 Context
            return GlobalTenantManager.getResolver().resolveTenant(request)
                .doOnNext(tenant -> {
                    setTenantId(tenant.getId());
                    setTenantRootInd(tenant.getRootInd());
                    log.info("[TenantContext] handleReactiveRequest end. resolved tenant [{}]", tenant.getId());
                })
                .then();
        } else {
            log.info("[TenantContext] handleReactiveRequest end. exist tenant [{}]", tenantId);
            return Mono.empty();
        }
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

    public static String getTenantIdAsString() {
        if (tenantId.get() == null) {
            return NumberUtils.toString(GlobalTenantManager.getStore().root().getId());
        }
        return NumberUtils.toString(tenantId.get());
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
