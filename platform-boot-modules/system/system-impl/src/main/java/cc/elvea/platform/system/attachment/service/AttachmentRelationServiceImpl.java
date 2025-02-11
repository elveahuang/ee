package cc.elvea.platform.system.attachment.service;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.attachment.model.entity.AttachmentRelationEntity;
import cc.elvea.platform.system.attachment.repository.AttachmentRelationRepository;
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
