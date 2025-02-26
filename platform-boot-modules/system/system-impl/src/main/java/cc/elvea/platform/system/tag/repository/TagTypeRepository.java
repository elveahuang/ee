package cc.elvea.platform.system.tag.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.tag.model.entity.TagTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface TagTypeRepository extends BaseEntityRepository<TagTypeEntity, Long> {
}
