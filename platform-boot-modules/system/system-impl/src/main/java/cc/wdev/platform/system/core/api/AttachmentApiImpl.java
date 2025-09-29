package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.core.storage.StorageFactory;
import cc.wdev.platform.commons.core.storage.model.FileObject;
import cc.wdev.platform.system.core.domain.AttachmentFile;
import cc.wdev.platform.system.core.domain.AttachmentParameter;
import cc.wdev.platform.system.core.domain.request.AttachmentRelationRequest;
import cc.wdev.platform.system.core.domain.request.AttachmentRelationSaveRequest;
import cc.wdev.platform.system.core.domain.request.AttachmentTypeRequest;
import cc.wdev.platform.system.core.domain.vo.AttachmentRelationVo;
import cc.wdev.platform.system.core.domain.vo.AttachmentTypeVo;
import cc.wdev.platform.system.core.domain.vo.AttachmentVo;
import cc.wdev.platform.system.core.service.AttachmentFileService;
import cc.wdev.platform.system.core.service.AttachmentTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class AttachmentApiImpl implements AttachmentApi {

    private final StorageFactory storage;

    private final AttachmentFileService attachmentService;

    private final AttachmentTypeService attachmentTypeService;

    /**
     * @see AttachmentApi#getAttachmentType(AttachmentTypeRequest)
     */
    @Override
    public AttachmentTypeVo getAttachmentType(AttachmentTypeRequest request) {
        return attachmentTypeService.getAttachmentType(request.getType());
    }

    /**
     * @see AttachmentApi#getAttachment(AttachmentRelationRequest)
     */
    @Override
    public AttachmentVo getAttachment(AttachmentRelationRequest request) {
        return AttachmentVo.builder().build();
    }

    /**
     * @see AttachmentApi#uploadAttachmentFile(AttachmentParameter, MultipartFile)
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
     * @see AttachmentApi#getAttachmentRelation(AttachmentRelationRequest)
     */
    @Override
    public AttachmentRelationVo getAttachmentRelation(AttachmentRelationRequest request) {
        return null;
    }

    /**
     * @see AttachmentApi#saveAttachmentRelation(AttachmentRelationSaveRequest)
     */
    @Override
    public void saveAttachmentRelation(AttachmentRelationSaveRequest request) {

    }

}
