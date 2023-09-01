package cn.elvea.platform.system.log.model.converter;

import cn.elvea.platform.commons.core.log.dto.OperationLogDto;
import cn.elvea.platform.system.log.model.entity.OperationLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface OperationLogConverter {

    OperationLogConverter INSTANCE = Mappers.getMapper(OperationLogConverter.class);

    OperationLogEntity dto2Entity(OperationLogDto dto);

}
