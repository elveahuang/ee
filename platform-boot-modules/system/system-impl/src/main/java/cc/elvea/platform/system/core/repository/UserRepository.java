package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface UserRepository extends BaseEntityRepository<UserEntity, Long> {
}
