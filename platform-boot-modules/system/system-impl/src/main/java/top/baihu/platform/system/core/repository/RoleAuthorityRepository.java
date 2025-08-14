package top.baihu.platform.system.core.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.core.domain.entity.RoleAuthorityEntity;

/**
 * @author elvea
 */
@Repository
public interface RoleAuthorityRepository extends BaseEntityRepository<RoleAuthorityEntity, Long> {
}
