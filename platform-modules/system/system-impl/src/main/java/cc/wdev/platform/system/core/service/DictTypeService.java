package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.DictTypeEntity;
import cc.wdev.platform.system.core.domain.vo.DictTypeVo;

/**
 * @author elvea
 */
public interface DictTypeService extends CachingEntityService<DictTypeEntity, Long> {

    /**
     * 获取指定字典类型ID
     */
    Long getDictTypeId(String code);

    /**
     * 获取指定字典类型
     */
    DictTypeVo getDictType(String code);

    /**
     * 获取指定编号的字典类型
     */
    DictTypeEntity findByCode(String code);

}
