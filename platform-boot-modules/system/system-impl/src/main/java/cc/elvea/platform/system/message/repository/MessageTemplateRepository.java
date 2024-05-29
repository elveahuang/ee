package cc.elvea.platform.system.message.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.message.model.entity.MessageTemplateEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface MessageTemplateRepository extends BaseEntityRepository<MessageTemplateEntity, Long> {
}
