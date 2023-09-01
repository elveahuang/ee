package cn.elvea.platform.lxp.core.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.lxp.core.model.entity.ProjectEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface ProjectRepository extends BaseEntityRepository<ProjectEntity, Long> {
}
