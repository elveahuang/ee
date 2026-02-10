package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.AttachmentTypeEntity;
import cc.wdev.platform.system.core.domain.vo.AttachmentTypeVo;

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
