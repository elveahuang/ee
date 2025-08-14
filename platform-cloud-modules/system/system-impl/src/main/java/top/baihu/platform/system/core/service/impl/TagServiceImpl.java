package top.baihu.platform.system.core.service.impl;

import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.TagEntity;
import top.baihu.platform.system.core.mapper.TagMapper;
import top.baihu.platform.system.core.service.TagService;

/**
 * @author elvea
 * @see TagService
 * @see BaseCachingEntityService
 */
@Service
public class TagServiceImpl extends BaseCachingEntityService<TagEntity, Long, TagMapper> implements TagService {
}
