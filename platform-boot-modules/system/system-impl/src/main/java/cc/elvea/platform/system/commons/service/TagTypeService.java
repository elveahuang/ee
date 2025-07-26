package cc.elvea.platform.system.commons.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.commons.model.entity.TagTypeEntity;
import cc.elvea.platform.system.commons.model.vo.TagTypeVo;

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
