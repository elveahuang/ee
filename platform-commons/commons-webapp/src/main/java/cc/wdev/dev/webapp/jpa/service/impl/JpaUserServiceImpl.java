package cc.wdev.dev.webapp.jpa.service.impl;

import cc.wdev.dev.webapp.jpa.domain.entity.JpaUserEntity;
import cc.wdev.dev.webapp.jpa.repository.JpaUserRepository;
import cc.wdev.dev.webapp.jpa.service.JpaUserService;
import cc.wdev.platform.commons.data.jpa.service.BaseEntityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class JpaUserServiceImpl extends BaseEntityService<JpaUserEntity, Long, JpaUserRepository> implements JpaUserService {
}
