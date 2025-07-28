package cc.elvea.platform.system.core.domain.converter;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.system.core.domain.entity.OperationLogEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface OperationLogConverter {

    OperationLogEntity dto2Entity(OperationLogDto dto);

}
