package top.baihu.platform.system.core.manager;

import org.springframework.web.multipart.MultipartFile;
import top.baihu.platform.system.core.domain.AttachmentFile;
import top.baihu.platform.system.core.domain.AttachmentParameter;
import top.baihu.platform.system.core.domain.request.AttachmentRelationRequest;
import top.baihu.platform.system.core.domain.request.AttachmentRelationSaveRequest;
import top.baihu.platform.system.core.domain.request.AttachmentTypeRequest;
import top.baihu.platform.system.core.domain.vo.AttachmentRelationVo;
import top.baihu.platform.system.core.domain.vo.AttachmentTypeVo;
import top.baihu.platform.system.core.domain.vo.AttachmentVo;

/**
 * @author elvea
 */
public interface AttachmentManager {

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
