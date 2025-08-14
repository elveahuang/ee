package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.baihu.platform.commons.core.storage.StorageFactory;
import top.baihu.platform.commons.core.storage.domain.FileObject;
import top.baihu.platform.system.core.domain.AttachmentFile;
import top.baihu.platform.system.core.domain.AttachmentParameter;
import top.baihu.platform.system.core.domain.request.AttachmentRelationRequest;
import top.baihu.platform.system.core.domain.request.AttachmentRelationSaveRequest;
import top.baihu.platform.system.core.domain.request.AttachmentTypeRequest;
import top.baihu.platform.system.core.domain.vo.AttachmentRelationVo;
import top.baihu.platform.system.core.domain.vo.AttachmentTypeVo;
import top.baihu.platform.system.core.domain.vo.AttachmentVo;
import top.baihu.platform.system.core.service.AttachmentFileService;
import top.baihu.platform.system.core.service.AttachmentTypeService;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class AttachmentManagerImpl implements AttachmentManager {

    private final StorageFactory storage;

    private final AttachmentFileService attachmentService;

    private final AttachmentTypeService attachmentTypeService;

    /**
     * @see AttachmentManager#getAttachmentType(AttachmentTypeRequest)
     */
    @Override
    public AttachmentTypeVo getAttachmentType(AttachmentTypeRequest request) {
        return attachmentTypeService.getAttachmentType(request.getType());
    }

    /**
     * @see AttachmentManager#getAttachment(AttachmentRelationRequest)
     */
    @Override
    public AttachmentVo getAttachment(AttachmentRelationRequest request) {
        return AttachmentVo.builder().build();
    }

    /**
     * @see AttachmentManager#uploadAttachmentFile(AttachmentParameter, MultipartFile)
     */
    @Override
    public AttachmentFile uploadAttachmentFile(AttachmentParameter parameter, MultipartFile file) {
        try {
            FileObject<?> object = this.storage.getStorageService().uploadFile(file);
            return AttachmentFile.builder().type(parameter.getType()).url(object.getUrl()).build();
        } catch (Exception e) {
            log.error("uploadAttachmentFile failed.", e);
        }
        return null;
    }

    /**
     * @see AttachmentManager#getAttachmentRelation(AttachmentRelationRequest)
     */
    @Override
    public AttachmentRelationVo getAttachmentRelation(AttachmentRelationRequest request) {
        return null;
    }

    /**
     * @see AttachmentManager#saveAttachmentRelation(AttachmentRelationSaveRequest)
     */
    @Override
    public void saveAttachmentRelation(AttachmentRelationSaveRequest request) {

    }

}
