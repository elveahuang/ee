package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.AttachmentRelationEntity;
import cc.wdev.platform.system.core.repository.AttachmentRelationRepository;
import cc.wdev.platform.system.core.service.AttachmentRelationFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class AttachmentRelationServiceImpl
    extends BaseCachingEntityService<AttachmentRelationEntity, Long, AttachmentRelationRepository>
    implements AttachmentRelationFileService {
}
