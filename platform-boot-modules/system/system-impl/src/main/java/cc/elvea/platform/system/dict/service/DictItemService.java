package cc.elvea.platform.system.dict.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.dict.model.entity.DictItemEntity;
import cc.elvea.platform.system.dict.model.request.DictRelationRequest;
import cc.elvea.platform.system.dict.model.request.DictSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author elvea
 */
public interface DictItemService extends CachingEntityService<DictItemEntity, Long> {

    /**
     * 搜索指定字典类型下面的字典项
     */
    Page<DictItemEntity> search(DictSearchRequest request);

    /**
     * 获取指定字典类型下属所有字典
     */
    List<DictItemEntity> findByTypeId(Long tagTypeId);

    /**
     * 获取目标实体关联的字典
     */
    List<DictItemEntity> findByTarget(DictRelationRequest request);

}
