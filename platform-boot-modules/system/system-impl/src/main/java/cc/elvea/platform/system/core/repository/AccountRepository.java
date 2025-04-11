package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.model.entity.AccountEntity;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface AccountRepository extends BaseEntityRepository<AccountEntity, Long> {
}
