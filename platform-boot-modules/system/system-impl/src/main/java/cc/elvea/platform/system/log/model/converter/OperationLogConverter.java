package cc.elvea.platform.system.log.model.converter;

import cc.elvea.platform.commons.logging.domain.OperationLogDto;
import cc.elvea.platform.system.log.model.entity.OperationLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface OperationLogConverter {

    OperationLogConverter INSTANCE = Mappers.getMapper(OperationLogConverter.class);

    OperationLogEntity dto2Entity(OperationLogDto dto);

}
