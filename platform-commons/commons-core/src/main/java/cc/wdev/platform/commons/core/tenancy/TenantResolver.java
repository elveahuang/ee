package cc.wdev.platform.commons.core.tenancy;

import cc.wdev.platform.commons.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
 */
public interface TenantResolver {

    Tenant resolveTenant(HttpServletRequest request);

    default Tenant resolveTenant() {
        return this.resolveTenant(ServletUtils.getRequest());
    }

    default Tenant getDefaultTenant() {
        return Tenant.builder().id(1L).build();
    }

}
