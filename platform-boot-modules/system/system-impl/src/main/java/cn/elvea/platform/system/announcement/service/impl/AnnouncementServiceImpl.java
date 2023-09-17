package cn.elvea.platform.system.announcement.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cn.elvea.platform.system.announcement.repository.AnnouncementRepository;
import cn.elvea.platform.system.announcement.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
public class AnnouncementServiceImpl
        extends BaseCachingEntityService<AnnouncementEntity, Long, AnnouncementRepository> implements AnnouncementService {
}
