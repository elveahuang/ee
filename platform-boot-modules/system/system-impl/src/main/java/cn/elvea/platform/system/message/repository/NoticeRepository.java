package cn.elvea.platform.system.message.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.message.model.entity.NoticeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface NoticeRepository extends BaseEntityRepository<NoticeEntity, Long> {
}