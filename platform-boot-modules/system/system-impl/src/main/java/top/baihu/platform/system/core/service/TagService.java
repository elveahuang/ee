package top.baihu.platform.system.core.service;

import org.springframework.data.domain.Page;
import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.TagEntity;
import top.baihu.platform.system.core.domain.request.TagRelationRequest;
import top.baihu.platform.system.core.domain.request.TagSearchRequest;

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
