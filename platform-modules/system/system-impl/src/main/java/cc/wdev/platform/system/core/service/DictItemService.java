package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.DictItemEntity;
import cc.wdev.platform.system.core.domain.request.DictRelationRequest;
import cc.wdev.platform.system.core.domain.request.DictSearchRequest;
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
