package top.baihu.platform.system.message.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.message.domain.entity.MessageTemplateEntity;

/**
 * @author elvea
 */
@Repository
public interface MessageTemplateRepository extends BaseEntityRepository<MessageTemplateEntity, Long> {
}
