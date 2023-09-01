package cn.elvea.platform.system.core.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface UserRepository extends BaseEntityRepository<UserEntity, Long> {
}
