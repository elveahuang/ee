package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.AttachmentFileEntity;
import cc.wdev.platform.system.core.repository.AttachmentFileRepository;
import cc.wdev.platform.system.core.service.AttachmentFileService;
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
