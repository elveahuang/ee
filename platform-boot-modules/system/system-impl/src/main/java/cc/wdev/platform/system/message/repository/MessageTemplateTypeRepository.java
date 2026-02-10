package cc.wdev.platform.system.message.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.message.domain.entity.MessageTemplateTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface MessageTemplateTypeRepository extends BaseEntityRepository<MessageTemplateTypeEntity, Long> {
}
