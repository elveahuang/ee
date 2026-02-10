package cc.wdev.platform.commons.core.tenant;

/**
 * @author elvea
 */
public interface TenantStore {

    default Tenant root() {
        return Tenant.defaultTenant;
    }

    default Tenant findById(Long id) {
        return this.root();
    }

    default Tenant findByCode(String code) {
        return this.root();
    }

}
