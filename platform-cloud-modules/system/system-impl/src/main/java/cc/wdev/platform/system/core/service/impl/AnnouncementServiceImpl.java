package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseEntityService;
import cc.wdev.platform.system.core.domain.entity.AnnouncementEntity;
import cc.wdev.platform.system.core.mapper.AnnouncementMapper;
import cc.wdev.platform.system.core.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see AnnouncementService
 */
@Slf4j
@Service
public class AnnouncementServiceImpl
    extends BaseEntityService<AnnouncementEntity, Long, AnnouncementMapper>
    implements AnnouncementService {
}
