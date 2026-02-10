package cc.wdev.dev.webapp.jpa.service;

import cc.wdev.dev.webapp.jpa.domain.entity.JpaUserEntity;
import cc.wdev.dev.webapp.jpa.repository.JpaUserRepository;
import cc.wdev.platform.commons.data.jpa.service.EnhancedEntityService;
import cc.wdev.platform.commons.service.EntityService;

public interface JpaUserService
    extends EntityService<JpaUserEntity, Long>, EnhancedEntityService<JpaUserEntity, Long, JpaUserRepository> {
}
