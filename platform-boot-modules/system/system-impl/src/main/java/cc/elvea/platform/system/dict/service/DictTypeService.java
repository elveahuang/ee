package cc.elvea.platform.system.dict.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.dict.model.entity.DictTypeEntity;
import cc.elvea.platform.system.dict.model.vo.DictTypeVo;

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
