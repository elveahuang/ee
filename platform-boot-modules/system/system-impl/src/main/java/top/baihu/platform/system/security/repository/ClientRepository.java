package top.baihu.platform.system.security.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.security.domain.entity.ClientEntity;

/**
 * @author elvea
 */
@Repository
public interface ClientRepository extends BaseEntityRepository<ClientEntity, Long> {
}
