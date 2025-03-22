package cc.elvea.platform.system.attachment.service;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.attachment.model.entity.AttachmentFileEntity;
import cc.elvea.platform.system.attachment.repository.AttachmentFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class AttachmentFileServiceImpl
        extends BaseCachingEntityService<AttachmentFileEntity, Long, AttachmentFileRepository>
        implements AttachmentFileService {
}
