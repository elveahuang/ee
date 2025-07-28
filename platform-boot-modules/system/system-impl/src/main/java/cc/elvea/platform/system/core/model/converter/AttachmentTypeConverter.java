package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.entity.AttachmentTypeEntity;
import cc.elvea.platform.system.core.model.vo.AttachmentTypeVo;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AttachmentTypeConverter {

    AttachmentTypeVo entity2Vo(AttachmentTypeEntity entity);

}
