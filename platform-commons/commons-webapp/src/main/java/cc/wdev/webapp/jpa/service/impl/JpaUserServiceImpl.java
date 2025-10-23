package cc.wdev.webapp.jpa.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseEntityService;
import cc.wdev.webapp.jpa.domain.entity.JpaUserEntity;
import cc.wdev.webapp.jpa.repository.JpaUserRepository;
import cc.wdev.webapp.jpa.service.JpaUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class JpaUserServiceImpl extends BaseEntityService<JpaUserEntity, Long, JpaUserRepository> implements JpaUserService {
}
