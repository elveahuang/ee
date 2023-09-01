package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.core.model.dto.PositionDeleteDto;
import cn.elvea.platform.system.core.model.dto.PositionDto;
import cn.elvea.platform.system.core.model.dto.PositionSaveDto;
import cn.elvea.platform.system.core.model.entity.PositionEntity;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface PositionService extends CachingEntityService<PositionEntity, Long> {

    /**
     * 保存岗位
     */
    PositionDto savePosition(PositionSaveDto saveDto);

    /**
     * 删除岗位
     */
    void deletePosition(PositionDeleteDto deleteDto);

    /**
     * 获取顶层岗位
     */
    PositionEntity getRootPosition();

    /**
     * 获取默认岗位
     */
    PositionEntity getDefaultPosition();

}
