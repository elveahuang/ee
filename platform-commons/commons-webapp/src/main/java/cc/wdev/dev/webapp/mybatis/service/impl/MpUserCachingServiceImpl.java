package cc.wdev.dev.webapp.mybatis.service.impl;

import cc.wdev.dev.webapp.mybatis.domain.entity.MpUserEntity;
import cc.wdev.dev.webapp.mybatis.mapper.MpUserMapper;
import cc.wdev.dev.webapp.mybatis.service.MpUserCachingService;
import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MpUserCachingServiceImpl extends BaseCachingEntityService<MpUserEntity, Long, MpUserMapper> implements MpUserCachingService {
}
