package cc.elvea.platform.system.attachment.api;

import cc.elvea.platform.commons.core.storage.StorageFactory;
import cc.elvea.platform.commons.core.storage.model.FileObject;
import cc.elvea.platform.system.attachment.model.AttachmentFile;
import cc.elvea.platform.system.attachment.model.AttachmentParameter;
import cc.elvea.platform.system.attachment.model.request.AttachmentRelationRequest;
import cc.elvea.platform.system.attachment.model.request.AttachmentRelationSaveRequest;
import cc.elvea.platform.system.attachment.model.request.AttachmentTypeRequest;
import cc.elvea.platform.system.attachment.model.vo.AttachmentRelationVo;
import cc.elvea.platform.system.attachment.model.vo.AttachmentTypeVo;
import cc.elvea.platform.system.attachment.model.vo.AttachmentVo;
import cc.elvea.platform.system.attachment.service.AttachmentFileService;
import cc.elvea.platform.system.attachment.service.AttachmentTypeService;
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
