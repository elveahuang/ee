package cc.elvea.platform.system.tag.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.tag.model.entity.TagEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface TagRepository extends BaseEntityRepository<TagEntity, Long> {
}
