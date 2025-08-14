package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.core.domain.dto.PositionDto;
import top.baihu.platform.system.core.domain.dto.PositionSaveDto;
import top.baihu.platform.system.core.domain.entity.PositionEntity;

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
