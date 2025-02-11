package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.mapper.TagRelationMapper;
import cc.elvea.platform.system.core.model.entity.TagRelationEntity;
import cc.elvea.platform.system.core.service.TagRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagRelationService
 * @see BaseCachingEntityService
 */
@Service
@Slf4j
public class TagRelationServiceImpl extends BaseCachingEntityService<TagRelationEntity, Long, TagRelationMapper> implements TagRelationService {
}
