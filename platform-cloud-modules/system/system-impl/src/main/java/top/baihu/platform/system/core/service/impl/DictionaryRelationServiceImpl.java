package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.DictionaryRelationEntity;
import top.baihu.platform.system.core.mapper.DictionaryRelationMapper;
import top.baihu.platform.system.core.service.DictionaryRelationService;

/**
 * @author elvea
 * @see DictionaryRelationService
 */
@Service
@Slf4j
public class DictionaryRelationServiceImpl extends BaseCachingEntityService<DictionaryRelationEntity, Long, DictionaryRelationMapper>
    implements DictionaryRelationService {
}
