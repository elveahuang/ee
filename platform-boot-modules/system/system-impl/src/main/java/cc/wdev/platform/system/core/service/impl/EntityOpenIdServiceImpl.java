package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.EntityOpenIdEntity;
import cc.wdev.platform.system.core.repository.EntityOpenIdRepository;
import cc.wdev.platform.system.core.service.EntityOpenIdService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see EntityOpenIdService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
@AllArgsConstructor
public class EntityOpenIdServiceImpl extends BaseCachingEntityService<EntityOpenIdEntity, Long, EntityOpenIdRepository> implements EntityOpenIdService {
}
