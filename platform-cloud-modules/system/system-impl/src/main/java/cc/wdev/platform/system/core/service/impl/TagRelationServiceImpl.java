package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.TagRelationEntity;
import cc.wdev.platform.system.core.mapper.TagRelationMapper;
import cc.wdev.platform.system.core.service.TagRelationService;
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
