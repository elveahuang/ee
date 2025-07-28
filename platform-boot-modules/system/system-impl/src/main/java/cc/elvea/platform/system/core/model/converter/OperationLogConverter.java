package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.system.core.model.entity.OperationLogEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface OperationLogConverter {

    OperationLogEntity dto2Entity(OperationLogDto dto);

}
