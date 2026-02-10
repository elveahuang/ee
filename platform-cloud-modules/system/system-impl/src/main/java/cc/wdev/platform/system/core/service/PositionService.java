package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.commons.service.EntityService;
import cc.wdev.platform.system.core.domain.dto.PositionDto;
import cc.wdev.platform.system.core.domain.dto.PositionSaveDto;
import cc.wdev.platform.system.core.domain.entity.PositionEntity;

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
