package cc.elvea.platform.system.mall.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.mall.domain.entity.AccountVipEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AccountVipRepository extends BaseEntityRepository<AccountVipEntity, Long> {
}
