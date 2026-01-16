package cc.wdev.dev.webapp.es.repository;

import cc.wdev.dev.webapp.es.domain.entity.CourseElasticEntity;
import cc.wdev.platform.commons.data.elasticsearch.repository.BaseEntityRepository;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface CourseElasticRepository extends BaseEntityRepository<CourseElasticEntity, Long> {
}
