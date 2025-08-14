package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.commons.core.log.domain.OperationLogDto;
import top.baihu.platform.system.core.domain.entity.OperationLogEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface OperationLogConverter {

    OperationLogEntity dto2Entity(OperationLogDto dto);

}
