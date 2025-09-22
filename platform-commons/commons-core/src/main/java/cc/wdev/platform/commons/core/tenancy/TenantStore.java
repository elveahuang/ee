package cc.wdev.platform.commons.core.tenancy;

public interface TenantStore {

    default Tenant findById(Long id) {
        return null;
    }

    default Tenant findByCode(String code) {
        return null;
    }

}
