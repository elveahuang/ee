package cc.wdev.platform.commons.core.tenant;

/**
 * @author elvea
 */
public interface TenantStore {

    default Tenant findById(Long id) {
        return Tenant.defaultTenant;
    }

    default Tenant findByCode(String code) {
        return Tenant.defaultTenant;
    }

}
