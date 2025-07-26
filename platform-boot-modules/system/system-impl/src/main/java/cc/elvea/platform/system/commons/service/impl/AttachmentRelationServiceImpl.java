package cc.elvea.platform.system.commons.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.commons.model.entity.AttachmentRelationEntity;
import cc.elvea.platform.system.commons.repository.AttachmentRelationRepository;
import cc.elvea.platform.system.commons.service.AttachmentRelationFileService;
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
