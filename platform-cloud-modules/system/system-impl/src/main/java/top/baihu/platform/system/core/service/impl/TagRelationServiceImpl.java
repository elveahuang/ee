package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.TagRelationEntity;
import top.baihu.platform.system.core.mapper.TagRelationMapper;
import top.baihu.platform.system.core.service.TagRelationService;

/**
 * @author elvea
 * @see TagRelationService
 * @see BaseCachingEntityService
 */
@Service
@Slf4j
public class TagRelationServiceImpl extends BaseCachingEntityService<TagRelationEntity, Long, TagRelationMapper> implements TagRelationService {
}
