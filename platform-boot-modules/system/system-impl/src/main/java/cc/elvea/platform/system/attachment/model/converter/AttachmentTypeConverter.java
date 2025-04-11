package cc.elvea.platform.system.attachment.model.converter;

import cc.elvea.platform.system.attachment.model.entity.AttachmentTypeEntity;
import cc.elvea.platform.system.attachment.model.vo.AttachmentTypeVo;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AttachmentTypeConverter {

    AttachmentTypeVo entity2Vo(AttachmentTypeEntity entity);

}
