package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.model.dto.PositionDto;
import cc.elvea.platform.system.core.model.dto.PositionSaveDto;
import cc.elvea.platform.system.core.model.entity.PositionEntity;

import java.util.List;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
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
