package cc.elvea.platform.system.message.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.message.domain.entity.MessageTemplateTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface MessageTemplateTypeRepository extends BaseEntityRepository<MessageTemplateTypeEntity, Long> {
}
