package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.cache.RoleAuthorityCacheKeyGenerator;
import cc.elvea.platform.system.core.cache.UserRoleCacheKeyGenerator;
import cc.elvea.platform.system.core.mapper.RoleAuthorityMapper;
import cc.elvea.platform.system.core.model.dto.RoleAuthoritySaveDto;
import cc.elvea.platform.system.core.model.entity.RoleAuthorityEntity;
import cc.elvea.platform.system.core.service.RoleAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see RoleAuthorityService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
public class RoleAuthorityManagerImpl extends BaseCachingEntityService<RoleAuthorityEntity, Long, RoleAuthorityMapper> implements RoleAuthorityService {

    private final RoleAuthorityCacheKeyGenerator cacheKeyGenerator = new RoleAuthorityCacheKeyGenerator();

    @Override
    public RoleAuthorityCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    /**
     * @see RoleAuthorityService#findRoleIdByAuthorityId(Long)
     */
    @Override
    public List<RoleAuthorityEntity> findByAuthorityId(Long authorityId) {
        return lambdaQueryWrapper().eq(RoleAuthorityEntity::getAuthorityId, authorityId).list();
    }

    /**
     * @see RoleAuthorityService#findRoleIdByAuthorityId(Long)
     */
    @Override
    public List<Long> findRoleIdByAuthorityId(Long authorityId) {
        return this.findByAuthorityId(authorityId).stream().map(RoleAuthorityEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * @see RoleAuthorityService#findByAuthorityId(List)
     */
    @Override
    public List<RoleAuthorityEntity> findByAuthorityId(List<Long> authorityIdList) {
        return CollectionUtils.isNotEmpty(authorityIdList) ?
                lambdaQueryWrapper().in(RoleAuthorityEntity::getAuthorityId, authorityIdList).list() : Collections.emptyList();
    }

    /**
     * @see RoleAuthorityService#findRoleIdByAuthorityId(List)
     */
    @Override
    public List<Long> findRoleIdByAuthorityId(List<Long> authorityIdList) {
        return CollectionUtils.isNotEmpty(authorityIdList) ?
                this.findByAuthorityId(authorityIdList).stream().map(RoleAuthorityEntity::getRoleId).collect(Collectors.toList()) :
                Collections.emptyList();
    }

    /**
     * @see RoleAuthorityService#findByRoleId(Long)
     */
    @Override
    public List<RoleAuthorityEntity> findByRoleId(Long roleId) {
        return lambdaQueryWrapper().eq(RoleAuthorityEntity::getRoleId, roleId).list();
    }

    /**
     * @see RoleAuthorityService#findAuthorityIdByRoleId(Long)
     */
    @Override
    public List<Long> findAuthorityIdByRoleId(Long roleId) {
        return this.findByRoleId(roleId).stream().map(RoleAuthorityEntity::getAuthorityId).collect(Collectors.toList());
    }

    /**
     * @see RoleAuthorityService#findByRoleId(List)
     */
    @Override
    public List<RoleAuthorityEntity> findByRoleId(List<Long> roleIdList) {
        return CollectionUtils.isNotEmpty(roleIdList) ?
                lambdaQueryWrapper().in(RoleAuthorityEntity::getRoleId, roleIdList).list() : Collections.emptyList();
    }

    /**
     * @see RoleAuthorityService#findAuthorityIdByRoleId(List)
     */
    @Override
    public List<Long> findAuthorityIdByRoleId(List<Long> roleIdList) {
        return CollectionUtils.isNotEmpty(roleIdList) ?
                this.findByRoleId(roleIdList).stream().map(RoleAuthorityEntity::getAuthorityId).collect(Collectors.toList()) :
                Collections.emptyList();
    }

    /**
     * @see RoleAuthorityService#saveRoleAuthority(RoleAuthoritySaveDto)
     */
    @Override
    public boolean saveRoleAuthority(RoleAuthoritySaveDto saveDto) {
        // 查询已有的关联记录
        List<RoleAuthorityEntity> roleAuthorityEntityList = this.findByRoleId(saveDto.getRoleId());
        // 删除已有的关联记录
        if (CollectionUtils.isNotEmpty(roleAuthorityEntityList)) {
            this.deleteBatch(roleAuthorityEntityList);
            log.info("UserRoleRepository.deleteByUserId<{}>. ", saveDto.getRoleId());
        }
        // 保存全新的关联记录
        if (CollectionUtils.isNotEmpty(saveDto.getAuthorityIdList())) {
            List<RoleAuthorityEntity> newRoleAuthorityEntityList = saveDto.getAuthorityIdList().stream().map(authorityId -> RoleAuthorityEntity.builder().roleId(saveDto.getRoleId()).authorityId(authorityId).build()).collect(Collectors.toList());
            this.saveBatch(newRoleAuthorityEntityList);
        }
        return true;
    }

    /**
     * @see RoleAuthorityService#deleteByAuthorityId(List)
     */
    @Override
    public boolean deleteByAuthorityId(List<Long> authorityIdList) {
        // 查询已有的关联记录
        List<RoleAuthorityEntity> roleAuthorityEntityList = CollectionUtils.toArrayList(this.findByIds(authorityIdList));
        //
        if (CollectionUtils.isNotEmpty(roleAuthorityEntityList)) {
            // 获取相关联的角色ID
            List<Long> roleIdList = roleAuthorityEntityList.stream().map(RoleAuthorityEntity::getRoleId).toList();
            // 获取相关联角色所关联的用户ID
            List<Long> userIdList = Collections.emptyList();
            //this.userRoleManager.findAllUserIdByRoleId(roleIdList);
            // 删除已有的关联记录
            this.deleteBatch(roleAuthorityEntityList);
            // 清空已有的角色权限缓存
            clearUserAuthority(userIdList);
            // 清空已有的角色权限缓存
            this.getCacheService().delete(roleIdList.stream().map(new RoleAuthorityCacheKeyGenerator()::key).toArray(CacheKey[]::new));
        }
        return false;
    }

    /**
     * 清理用户ID相关的缓存
     *
     * @param userIdList 用户ID
     */
    private void clearUserAuthority(List<Long> userIdList) {
        this.getCacheService().delete(userIdList.stream().map(new UserRoleCacheKeyGenerator()::key).toArray(CacheKey[]::new));
    }

}
