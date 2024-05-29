package cc.elvea.platform.system.attachment.model.converter;

import cc.elvea.platform.system.attachment.model.entity.AttachmentTypeEntity;
import cc.elvea.platform.system.attachment.model.vo.AttachmentTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface AttachmentTypeConverter {

    AttachmentTypeConverter INSTANCE = Mappers.getMapper(AttachmentTypeConverter.class);

    AttachmentTypeVo entity2Vo(AttachmentTypeEntity entity);

}
