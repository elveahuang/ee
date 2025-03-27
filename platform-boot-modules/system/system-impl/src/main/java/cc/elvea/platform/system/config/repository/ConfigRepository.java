package cc.elvea.platform.system.config.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.config.model.entity.ConfigEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface ConfigRepository extends BaseEntityRepository<ConfigEntity, Long> {
}
