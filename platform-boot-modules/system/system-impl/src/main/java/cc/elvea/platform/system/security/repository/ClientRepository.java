package cc.elvea.platform.system.security.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.security.model.entity.ClientEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface ClientRepository extends BaseEntityRepository<ClientEntity, Long> {
}
