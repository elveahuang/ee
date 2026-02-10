package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.commons.core.log.domain.OperationLogDto;
import cc.wdev.platform.system.core.domain.entity.OperationLogEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface OperationLogConverter {

    OperationLogEntity dto2Entity(OperationLogDto dto);

}
