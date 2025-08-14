package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.TagTypeEntity;
import top.baihu.platform.system.core.domain.vo.TagTypeVo;

/**
 * @author elvea
 */
public interface TagTypeService extends CachingEntityService<TagTypeEntity, Long> {

    /**
     * 获取指定标签类型ID
     */
    Long getTagTypeId(String code);

    /**
     * 获取指定标签类型
     */
    TagTypeVo getTagType(String code);

    /**
     * 获取指定编号的标签类型
     */
    TagTypeEntity findByCode(String code);

}
