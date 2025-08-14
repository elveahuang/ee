package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.AttachmentRelationEntity;
import top.baihu.platform.system.core.repository.AttachmentRelationRepository;
import top.baihu.platform.system.core.service.AttachmentRelationFileService;

/**
 * @author elvea
 */
@Slf4j
@Service
public class AttachmentRelationServiceImpl
    extends BaseCachingEntityService<AttachmentRelationEntity, Long, AttachmentRelationRepository>
    implements AttachmentRelationFileService {
}
