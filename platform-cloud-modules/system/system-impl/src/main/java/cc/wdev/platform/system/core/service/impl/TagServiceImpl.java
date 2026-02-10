package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.TagEntity;
import cc.wdev.platform.system.core.mapper.TagMapper;
import cc.wdev.platform.system.core.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagService
 * @see BaseCachingEntityService
 */
@Service
public class TagServiceImpl extends BaseCachingEntityService<TagEntity, Long, TagMapper> implements TagService {
}
