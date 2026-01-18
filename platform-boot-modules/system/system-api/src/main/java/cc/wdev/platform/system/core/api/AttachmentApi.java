package cc.wdev.platform.system.core.api;

import cc.wdev.platform.system.core.domain.AttachmentFile;
import cc.wdev.platform.system.core.domain.AttachmentParameter;
import cc.wdev.platform.system.core.domain.request.AttachmentRelationRequest;
import cc.wdev.platform.system.core.domain.request.AttachmentRelationSaveRequest;
import cc.wdev.platform.system.core.domain.request.AttachmentTypeRequest;
import cc.wdev.platform.system.core.domain.vo.AttachmentRelationVo;
import cc.wdev.platform.system.core.domain.vo.AttachmentTypeVo;
import cc.wdev.platform.system.core.domain.vo.AttachmentVo;
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
