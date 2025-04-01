package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.commons.constants.SystemConstants;
import cc.elvea.platform.system.core.cache.PositionCacheKeyGenerator;
import cc.elvea.platform.system.core.cache.UserPositionCacheKeyGenerator;
import cc.elvea.platform.system.core.mapper.PositionMapper;
import cc.elvea.platform.system.core.model.converter.PositionConverter;
import cc.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cc.elvea.platform.system.core.model.dto.PositionDto;
import cc.elvea.platform.system.core.model.dto.PositionSaveDto;
import cc.elvea.platform.system.core.model.entity.PositionEntity;
import cc.elvea.platform.system.core.service.EntityRelationService;
import cc.elvea.platform.system.core.service.PositionService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see PositionService
 */
@Service
public class PositionServiceImpl extends BaseCachingEntityService<PositionEntity, Long, PositionMapper> implements PositionService {

    private final PositionCacheKeyGenerator cacheKeyGenerator = new PositionCacheKeyGenerator();

    private final EntityRelationService entityRelationService;

    public PositionServiceImpl(EntityRelationService entityRelationService) {
        this.entityRelationService = entityRelationService;
    }

    @Override
    public PositionCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    /**
     * @see PositionService#findByUserId(Long)
     */
    @Override
    public List<PositionEntity> findByUserId(Long userId) {
        //
        CacheKey userPositionCacheKey = new UserPositionCacheKeyGenerator().key(userId);
        //
        List<PositionEntity> userPositionEntityList = Lists.newArrayList();
        List<Long> userPositionIdList = getCacheService().get(userPositionCacheKey, k -> {
            List<Long> positionIdList = this.entityRelationService.getDirectPositionByUserId(userId);
            userPositionEntityList.addAll(this.findCacheByIds(positionIdList));
            return userPositionEntityList.stream().map(PositionEntity::getId).collect(Collectors.toList());
        });
        //
        if (CollectionUtils.isNotEmpty(userPositionEntityList)) {
            userPositionEntityList.forEach(this::setCache);
        } else {
            userPositionEntityList.addAll(findCacheByIds(userPositionIdList));
        }
        return userPositionEntityList;
    }

    /**
     * @see PositionService#savePosition(PositionSaveDto)
     */
    @Override
    public PositionDto savePosition(PositionSaveDto saveDto) {
        if (!StringUtils.isNotEmpty(saveDto.getCode())) {
            saveDto.setCode(generateCode("ORG"));
        }

        // 保存组织基本信息
        PositionEntity entity = SpringUtils.getBean(PositionConverter.class).saveDtoToEntity(saveDto);
        this.save(entity);

        // 保存组织父子关联
        EntityRelationSaveDto relationSaveDto = EntityRelationSaveDto.builder()
                .relationType(SystemConstants.ERN_PST_PARENT_PST)
                .entityId(entity.getId())
                .ancestorIdList(Lists.newArrayList(saveDto.getParentId()))
                .build();
        this.entityRelationService.saveEntityRelation(relationSaveDto);

        return SpringUtils.getBean(PositionConverter.class).entityToDto(entity);
    }

}
