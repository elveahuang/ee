package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.cache.UserRoleCacheKeyGenerator;
import cc.elvea.platform.system.core.mapper.UserRoleMapper;
import cc.elvea.platform.system.core.model.dto.UserRoleSaveDto;
import cc.elvea.platform.system.core.model.entity.UserRoleEntity;
import cc.elvea.platform.system.core.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see UserRoleService
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends BaseCachingEntityService<UserRoleEntity, Long, UserRoleMapper> implements UserRoleService {

    private final UserRoleCacheKeyGenerator cacheKeyGenerator = new UserRoleCacheKeyGenerator();

    @Override
    public UserRoleCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    /**
     * @see UserRoleService#findByUserId(Long)
     */
    @Override
    public List<UserRoleEntity> findByUserId(Long userId) {
        UserRoleEntity condition = UserRoleEntity.builder().userId(userId).build();
        List<UserRoleEntity> userRoleEntityList = this.getMapper().selectList(new QueryWrapper<>(condition));
        return (CollectionUtils.isNotEmpty(userRoleEntityList)) ? userRoleEntityList : Collections.emptyList();
    }

    /**
     * @see UserRoleService#findRoleIdByUserId(Long)
     */
    @Override
    public List<Long> findRoleIdByUserId(Long userId) {
        return this.findByUserId(userId).stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * @see UserRoleService#findAllRoleIdByUserId(List)
     */
    @Override
    public List<UserRoleEntity> findAllByUserId(List<Long> userIdList) {
        List<UserRoleEntity> entityList = lambdaQueryWrapper().in(UserRoleEntity::getUserId, userIdList).list();
        return (CollectionUtils.isNotEmpty(entityList)) ? entityList : Collections.emptyList();
    }

    /**
     * @see UserRoleService#findAllRoleIdByUserId(List)
     */
    @Override
    public List<Long> findAllRoleIdByUserId(List<Long> userIdList) {
        return this.findAllByUserId(userIdList).stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * @see UserRoleService#findByRoleId(Long)
     */
    @Override
    public List<UserRoleEntity> findByRoleId(Long roleId) {
        UserRoleEntity condition = UserRoleEntity.builder().roleId(roleId).build();
        List<UserRoleEntity> userRoleEntityList = this.getMapper().selectList(new QueryWrapper<>(condition));
        return (CollectionUtils.isNotEmpty(userRoleEntityList)) ? userRoleEntityList : Collections.emptyList();
    }

    /**
     * @see UserRoleService#findUserIdByRoleId(Long)
     */
    @Override
    public List<Long> findUserIdByRoleId(Long roleId) {
        return this.findByRoleId(roleId).stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * @see UserRoleService#findAllByRoleId(List)
     */
    @Override
    public List<UserRoleEntity> findAllByRoleId(List<Long> roleIdList) {
        List<UserRoleEntity> entityList = lambdaQueryWrapper().in(UserRoleEntity::getRoleId, roleIdList).list();
        return (CollectionUtils.isNotEmpty(entityList)) ? entityList : Collections.emptyList();
    }

    /**
     * @see UserRoleService#findAllUserIdByRoleId(List)
     */
    @Override
    public List<Long> findAllUserIdByRoleId(List<Long> roleIdList) {
        return this.findAllByRoleId(roleIdList).stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * @see UserRoleService#saveUserRole(UserRoleSaveDto)
     */
    @Override
    public boolean saveUserRole(UserRoleSaveDto saveDto) {
        // 查询已有的关联记录
        List<UserRoleEntity> userRoleEntityList = this.findByUserId(saveDto.getUserId());
        // 删除已有的关联记录
        if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
            this.deleteBatch(userRoleEntityList);
            log.info("UserRoleRepository.deleteByUserId<{}>. ", saveDto.getUserId());
        }
        // 保存全新的关联记录
        if (CollectionUtils.isNotEmpty(saveDto.getRoleIdList())) {
            List<UserRoleEntity> newUserRoleEntityList = saveDto.getRoleIdList().stream().map(roleId -> UserRoleEntity.builder().userId(saveDto.getUserId()).roleId(roleId).build()).collect(Collectors.toList());
            this.saveBatch(newUserRoleEntityList);
        }
        return true;
    }

}
