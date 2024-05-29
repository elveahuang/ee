package cc.elvea.platform.system.message.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.message.model.entity.MessageUserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface MessageUserRepository extends BaseEntityRepository<MessageUserEntity, Long> {
}
