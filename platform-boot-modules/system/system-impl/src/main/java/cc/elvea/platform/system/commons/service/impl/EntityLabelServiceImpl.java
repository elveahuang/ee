package cc.elvea.platform.system.commons.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.commons.model.entity.EntityLabelEntity;
import cc.elvea.platform.system.commons.repository.EntityLabelRepository;
import cc.elvea.platform.system.commons.service.EntityLabelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
