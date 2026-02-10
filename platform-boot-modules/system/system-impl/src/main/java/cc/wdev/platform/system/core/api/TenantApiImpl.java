package cc.wdev.platform.system.core.api;

import cc.wdev.platform.system.core.domain.converter.TenantConverter;
import cc.wdev.platform.system.core.domain.dto.TenantDto;
import cc.wdev.platform.system.core.service.TenantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class TenantApiImpl implements TenantApi {

    private final TenantService tenantService;

    /**
     * @see TenantApi#findById(Long)
     */
    @Override
    public TenantDto findById(Long id) {
        return TenantConverter.INSTANCE.entity2Dto(this.tenantService.findById(id));
    }

    /**
     * @see TenantApi#findByCode(String)
     */
    @Override
    public TenantDto findByCode(String code) {
        return TenantConverter.INSTANCE.entity2Dto(this.tenantService.findByCode(code));
    }

}
