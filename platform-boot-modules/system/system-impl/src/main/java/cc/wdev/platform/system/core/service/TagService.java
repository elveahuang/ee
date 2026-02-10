package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.TagEntity;
import cc.wdev.platform.system.core.domain.request.TagRelationRequest;
import cc.wdev.platform.system.core.domain.request.TagSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author elvea
 */
public interface TagService extends CachingEntityService<TagEntity, Long> {

    /**
     * 搜索指定标签类型下面的标签
     */
    Page<TagEntity> search(TagSearchRequest request);

    /**
     * 获取指定标签类型下属所有标签
     */
    List<TagEntity> findByTypeId(Long typeId);

    /**
     * 获取目标实体关联的标签
     */
    List<TagEntity> findByTarget(TagRelationRequest request);

}
