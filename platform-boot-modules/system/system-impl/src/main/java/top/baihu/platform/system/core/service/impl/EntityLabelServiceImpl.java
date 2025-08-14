package top.baihu.platform.system.core.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.EntityLabelEntity;
import top.baihu.platform.system.core.repository.EntityLabelRepository;
import top.baihu.platform.system.core.service.EntityLabelService;

/**
 * @author elvea
 * @see EntityLabelService
 * @see BaseCachingEntityService
 */
@Slf4j
@AllArgsConstructor
@Service
public class EntityLabelServiceImpl extends BaseCachingEntityService<EntityLabelEntity, Long, EntityLabelRepository> implements EntityLabelService {
}
