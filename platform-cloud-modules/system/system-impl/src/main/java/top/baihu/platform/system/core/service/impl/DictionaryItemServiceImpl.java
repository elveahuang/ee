package top.baihu.platform.system.core.service.impl;

import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.DictionaryItemEntity;
import top.baihu.platform.system.core.mapper.DictionaryItemMapper;
import top.baihu.platform.system.core.service.DictionaryItemService;

/**
 * @author elvea
 * @see DictionaryItemService
 */
@Service
public class DictionaryItemServiceImpl extends BaseCachingEntityService<DictionaryItemEntity, Long, DictionaryItemMapper> implements DictionaryItemService {
}
