package cc.elvea.platform.system.banner.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.banner.model.entity.BannerEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface BannerRepository extends BaseEntityRepository<BannerEntity, Long> {
}
