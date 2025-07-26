package cc.elvea.platform.system.commons.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.commons.model.entity.TagEntity;
import cc.elvea.platform.system.commons.model.request.TagRelationRequest;
import cc.elvea.platform.system.commons.model.request.TagSearchRequest;
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
