package cc.elvea.platform.system.announcement.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseEntityService;
import cc.elvea.platform.system.announcement.domain.entity.AnnouncementEntity;
import cc.elvea.platform.system.announcement.mapper.AnnouncementMapper;
import cc.elvea.platform.system.announcement.service.AnnouncementService;
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
