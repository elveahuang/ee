package cc.wdev.platform.commons.core.tenant;

import jakarta.servlet.http.HttpServletRequest;

import static cc.wdev.platform.commons.constants.GlobalConstants.TENANT_CODE_KEY;
import static cc.wdev.platform.commons.constants.GlobalConstants.TENANT_ID_KEY;

/**
 * @author elvea
 */
public record DefaultTenantResolver(TenantStore tenantStore) implements TenantResolver {

    @Override
    public Tenant resolveTenant(HttpServletRequest request) {
        Tenant defaultTenant = getDefaultTenant();
        if (request.getHeader(TENANT_ID_KEY) != null) {
            return tenantStore.findById(Long.valueOf(request.getHeader(TENANT_ID_KEY)));
        } else if (request.getHeader(TENANT_CODE_KEY) != null) {
            return tenantStore.findByCode(request.getHeader(TENANT_CODE_KEY));
        }
        return defaultTenant;
    }

}
