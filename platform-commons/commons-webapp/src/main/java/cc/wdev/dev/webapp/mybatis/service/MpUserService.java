package cc.wdev.dev.webapp.mybatis.service;

import cc.wdev.dev.webapp.mybatis.domain.entity.MpUserEntity;
import cc.wdev.dev.webapp.mybatis.mapper.MpUserMapper;
import cc.wdev.platform.commons.data.mybatis.service.EnhancedEntityService;
import cc.wdev.platform.commons.service.EntityService;

public interface MpUserService
    extends EntityService<MpUserEntity, Long>, EnhancedEntityService<MpUserEntity, Long, MpUserMapper> {
}
