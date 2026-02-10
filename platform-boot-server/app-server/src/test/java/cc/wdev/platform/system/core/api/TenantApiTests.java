package cc.wdev.platform.system.core.api;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.commons.core.tenant.Tenant;
import cc.wdev.platform.system.core.domain.converter.TenantConverter;
import cc.wdev.platform.system.core.domain.dto.TenantDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class TenantApiTests extends BaseTests {

    @Autowired
    TenantApi tenantApi;

    @Autowired
    TenantConverter tenantConverter;

    @Test
    public void baseTest() {
        TenantDto dto = this.tenantApi.findByCode("ROOT");
        Assertions.assertNotNull(dto);
        Tenant tenant = tenantConverter.dto2Tenant(dto);
        Assertions.assertEquals(dto.getId(), tenant.getId());
    }

}
