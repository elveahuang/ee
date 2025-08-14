package top.baihu.platform.system.mall.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.mall.domain.entity.AccountVipLogEntity;

/**
 * @author elvea
 */
@Repository
public interface AccountVipLogRepository extends BaseEntityRepository<AccountVipLogEntity, Long> {
}
