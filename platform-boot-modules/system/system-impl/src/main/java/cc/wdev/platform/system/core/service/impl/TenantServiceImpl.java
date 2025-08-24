package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.TenantEntity;
import cc.wdev.platform.system.core.repository.TenantRepository;
import cc.wdev.platform.system.core.service.TenantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TenantService
 * @see BaseCachingEntityService
 */
@Slf4j
@AllArgsConstructor
@Service
public class TenantServiceImpl extends BaseCachingEntityService<TenantEntity, Long, TenantRepository> implements TenantService {
}
