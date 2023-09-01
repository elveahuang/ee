package cn.elvea.platform.system.security.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.security.model.entity.ClientEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface ClientRepository extends BaseEntityRepository<ClientEntity, Long> {
}
