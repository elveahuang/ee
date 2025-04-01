package cc.elvea.platform.system.attachment.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.attachment.model.entity.AttachmentTypeEntity;
import cc.elvea.platform.system.attachment.model.vo.AttachmentTypeVo;

/**
 * @author elvea
 */
public interface AttachmentTypeService extends CachingEntityService<AttachmentTypeEntity, Long> {

    /**
     * 获取指定附件类型ID
     */
    Long getAttachmentTypeId(String code);

    /**
     * 获取指定附件类型
     */
    AttachmentTypeVo getAttachmentType(String code);

    /**
     * 获取指定编号附件类型
     */
    AttachmentTypeEntity findByCode(String code);

}
