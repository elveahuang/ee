package cc.elvea.platform.system.message.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.message.model.entity.MessageTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface MessageTypeRepository extends BaseEntityRepository<MessageTypeEntity, Long> {
}
