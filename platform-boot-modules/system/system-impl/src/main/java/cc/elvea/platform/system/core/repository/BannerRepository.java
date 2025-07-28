package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.domain.entity.BannerEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface BannerRepository extends BaseEntityRepository<BannerEntity, Long> {
}
