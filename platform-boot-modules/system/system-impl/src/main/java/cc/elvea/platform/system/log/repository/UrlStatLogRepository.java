package cc.elvea.platform.system.log.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.commons.logging.domain.UrlStatLogDto;
import cc.elvea.platform.system.log.model.entity.UrlStatLogEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface UrlStatLogRepository extends BaseEntityRepository<UrlStatLogEntity, Long> {

    @Query("""
            SELECT new cc.elvea.platform.commons.logging.domain.UrlStatLogDto(t.path, avg(t.execTime), max(t.execTime), min(t.execTime), sum(t.execTime), count(t.id))
            FROM UrlLogEntity t
            where t.path = :path
            """)
    UrlStatLogDto getStatData(@Param("path") String path);

}
