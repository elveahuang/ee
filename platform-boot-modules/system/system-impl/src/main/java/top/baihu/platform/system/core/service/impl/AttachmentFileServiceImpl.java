package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.AttachmentFileEntity;
import top.baihu.platform.system.core.repository.AttachmentFileRepository;
import top.baihu.platform.system.core.service.AttachmentFileService;

/**
 * @author elvea
 */
@Slf4j
@Service
public class AttachmentFileServiceImpl
    extends BaseCachingEntityService<AttachmentFileEntity, Long, AttachmentFileRepository>
    implements AttachmentFileService {
}
