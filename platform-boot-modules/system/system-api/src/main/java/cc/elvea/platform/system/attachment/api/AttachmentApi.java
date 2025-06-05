package cc.elvea.platform.system.attachment.api;

import cc.elvea.platform.system.attachment.model.AttachmentFile;
import cc.elvea.platform.system.attachment.model.AttachmentParameter;
import cc.elvea.platform.system.attachment.model.request.AttachmentRelationRequest;
import cc.elvea.platform.system.attachment.model.request.AttachmentRelationSaveRequest;
import cc.elvea.platform.system.attachment.model.request.AttachmentTypeRequest;
import cc.elvea.platform.system.attachment.model.vo.AttachmentRelationVo;
import cc.elvea.platform.system.attachment.model.vo.AttachmentTypeVo;
import cc.elvea.platform.system.attachment.model.vo.AttachmentVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author elvea
 */
public interface AttachmentApi {

    /**
     * 获取附件类型
     */
    AttachmentTypeVo getAttachmentType(AttachmentTypeRequest request);

    /**
     * 获取附件
     */
    AttachmentVo getAttachment(AttachmentRelationRequest request);

    /**
     * 上传文件
     */
    AttachmentFile uploadAttachmentFile(AttachmentParameter parameter, MultipartFile file);

    /**
     * 获取文件关联
     */
    AttachmentRelationVo getAttachmentRelation(AttachmentRelationRequest request);

    /**
     * 保存文件关联
     */
    void saveAttachmentRelation(AttachmentRelationSaveRequest request);

}
