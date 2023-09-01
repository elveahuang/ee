package cn.elvea.platform.system.i18n.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.i18n.model.entity.EntityLabelEntity;
import cn.elvea.platform.system.i18n.repository.EntityLabelRepository;
import cn.elvea.platform.system.i18n.service.EntityLabelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see EntityLabelService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Service
public class EntityLabelServiceImpl extends BaseCachingEntityService<EntityLabelEntity, Long, EntityLabelRepository> implements EntityLabelService {
}
