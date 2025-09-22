package cc.wdev.platform.commons.data.jpa.tenancy;

import cc.wdev.platform.commons.core.tenancy.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * @author elvea
 */
public class CustomTenantIdentifierResolver implements CurrentTenantIdentifierResolver<Long> {

    @Override
    public Long resolveCurrentTenantIdentifier() {
        return TenantContext.getTenantId();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
