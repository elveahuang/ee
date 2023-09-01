package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.core.model.dto.PositionDto;
import cn.elvea.platform.system.core.model.dto.PositionSaveDto;
import cn.elvea.platform.system.core.model.entity.PositionEntity;

import java.util.List;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @since 0.0.1
 */
public interface PositionService extends CachingEntityService<PositionEntity, Long> {

    /**
     * 获取指定用户所属的岗位
     *
     * @param userId 用户ID
     * @return 用户所属的岗位
     */
    List<PositionEntity> findByUserId(Long userId);

    /**
     * 保存岗位
     *
     * @param saveDto {@link PositionSaveDto}
     * @return {@link PositionDto}
     */
    PositionDto savePosition(PositionSaveDto saveDto);

}
