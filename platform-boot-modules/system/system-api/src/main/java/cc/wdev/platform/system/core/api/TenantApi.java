package cc.wdev.platform.system.core.api;

import cc.wdev.platform.system.core.domain.dto.TenantDto;

/**
 * @author elvea
 */
public interface TenantApi {

    TenantDto findById(Long id);

    TenantDto findByCode(String code);

}
