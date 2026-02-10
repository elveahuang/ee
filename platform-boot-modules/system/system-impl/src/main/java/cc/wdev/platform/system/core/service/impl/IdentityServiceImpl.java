package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.IdentityEntity;
import cc.wdev.platform.system.core.repository.IdentityRepository;
import cc.wdev.platform.system.core.service.IdentityService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
public class IdentityServiceImpl extends BaseCachingEntityService<IdentityEntity, Long, IdentityRepository> implements IdentityService {
}
