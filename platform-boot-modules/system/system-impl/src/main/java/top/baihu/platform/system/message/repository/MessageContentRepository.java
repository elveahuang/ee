package top.baihu.platform.system.message.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.message.domain.entity.MessageContentEntity;

/**
 * @author elvea
 */
@Repository
public interface MessageContentRepository extends BaseEntityRepository<MessageContentEntity, Long> {
}
