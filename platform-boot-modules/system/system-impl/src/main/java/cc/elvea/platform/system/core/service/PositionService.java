package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.domain.dto.PositionDeleteDto;
import cc.elvea.platform.system.core.domain.dto.PositionDto;
import cc.elvea.platform.system.core.domain.dto.PositionSaveDto;
import cc.elvea.platform.system.core.domain.entity.PositionEntity;

/**
 * @author elvea
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
