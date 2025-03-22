package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.mapper.DictionaryTypeMapper;
import cc.elvea.platform.system.core.model.entity.DictionaryTypeEntity;
import cc.elvea.platform.system.core.service.DictionaryTypeService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see DictionaryTypeService
 */
@Service
public class DictionaryGroupServiceImpl
        extends BaseCachingEntityService<DictionaryTypeEntity, Long, DictionaryTypeMapper>
        implements DictionaryTypeService {
}
