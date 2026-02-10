package cc.wdev.dev.webapp.mybatis.service.impl;

import cc.wdev.dev.webapp.mybatis.domain.entity.MpUserEntity;
import cc.wdev.dev.webapp.mybatis.mapper.MpUserMapper;
import cc.wdev.dev.webapp.mybatis.service.MpUserService;
import cc.wdev.platform.commons.data.mybatis.service.BaseEntityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MpUserServiceImpl extends BaseEntityService<MpUserEntity, Long, MpUserMapper> implements MpUserService {
}
