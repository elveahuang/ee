package cc.wdev.dev.webapp.mybatis.service;

import cc.wdev.dev.webapp.mybatis.domain.entity.MpUserEntity;
import cc.wdev.dev.webapp.mybatis.mapper.MpUserMapper;
import cc.wdev.platform.commons.data.mybatis.service.EnhancedEntityService;
import cc.wdev.platform.commons.service.CachingEntityService;

public interface MpUserCachingService
    extends CachingEntityService<MpUserEntity, Long>, EnhancedEntityService<MpUserEntity, Long, MpUserMapper> {
}
