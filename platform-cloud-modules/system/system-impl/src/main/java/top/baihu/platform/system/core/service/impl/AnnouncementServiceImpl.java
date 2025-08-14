package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseEntityService;
import top.baihu.platform.system.core.domain.entity.AnnouncementEntity;
import top.baihu.platform.system.core.mapper.AnnouncementMapper;
import top.baihu.platform.system.core.service.AnnouncementService;

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
