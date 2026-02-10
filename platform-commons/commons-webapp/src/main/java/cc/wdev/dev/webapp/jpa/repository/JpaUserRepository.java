package cc.wdev.dev.webapp.jpa.repository;

import cc.wdev.dev.webapp.jpa.domain.entity.JpaUserEntity;
import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends BaseEntityRepository<JpaUserEntity, Long> {
}
