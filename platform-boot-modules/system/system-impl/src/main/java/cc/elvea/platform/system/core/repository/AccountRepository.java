package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.model.entity.AccountEntity;
import org.springframework.stereotype.Repository;

/**
 * @since 24.1.0
 */
@Repository
public interface AccountRepository extends BaseEntityRepository<AccountEntity, Long> {
}
