package cc.wdev.platform.commons.core.tenant;

import jakarta.servlet.http.HttpServletRequest;

import static cc.wdev.platform.commons.constants.GlobalConstants.TENANT_CODE_KEY;
import static cc.wdev.platform.commons.constants.GlobalConstants.TENANT_ID_KEY;

/**
 * @author elvea
 */
public record DefaultTenantResolver(TenantStore store, TenantConfig config) implements TenantResolver {

    /**
     * @see TenantResolver#resolveTenant(HttpServletRequest)
     */
    @Override
    public Tenant resolveTenant(HttpServletRequest request) {
        if (this.config.isEnabled()) {
            Tenant defaultTenant = getDefaultTenant();
            if (request.getHeader(TENANT_ID_KEY) != null) {
                return this.store.findById(Long.valueOf(request.getHeader(TENANT_ID_KEY)));
            } else if (request.getHeader(TENANT_CODE_KEY) != null) {
                return this.store.findByCode(request.getHeader(TENANT_CODE_KEY));
            }
            return defaultTenant;
        } else {
            return this.store.root();
        }
    }

    /**
     * @see TenantResolver#getDefaultTenant()
     */
    @Override
    public Tenant getDefaultTenant() {
        return this.store.root();
    }

}
