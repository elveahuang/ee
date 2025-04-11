package cc.elvea.platform.lxp.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.lxp.core.model.entity.ProjectEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface ProjectRepository extends BaseEntityRepository<ProjectEntity, Long> {
}
