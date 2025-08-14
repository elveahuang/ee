package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.dto.PositionDeleteDto;
import top.baihu.platform.system.core.domain.dto.PositionDto;
import top.baihu.platform.system.core.domain.dto.PositionSaveDto;
import top.baihu.platform.system.core.domain.entity.PositionEntity;

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
