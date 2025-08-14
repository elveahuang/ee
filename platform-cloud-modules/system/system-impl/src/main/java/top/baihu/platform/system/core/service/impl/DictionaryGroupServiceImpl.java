package top.baihu.platform.system.core.service.impl;

import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.DictionaryTypeEntity;
import top.baihu.platform.system.core.mapper.DictionaryTypeMapper;
import top.baihu.platform.system.core.service.DictionaryTypeService;

/**
 * @author elvea
 * @see DictionaryTypeService
 */
@Service
public class DictionaryGroupServiceImpl
    extends BaseCachingEntityService<DictionaryTypeEntity, Long, DictionaryTypeMapper>
    implements DictionaryTypeService {
}
