package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.entity.AttachmentTypeEntity;
import cc.wdev.platform.system.core.domain.vo.AttachmentTypeVo;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AttachmentTypeConverter {

    AttachmentTypeVo entity2Vo(AttachmentTypeEntity entity);

}
