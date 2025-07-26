package cc.elvea.platform.system.commons.model.converter;

import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.system.commons.model.entity.UrlLogEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface UrlLogConverter {

    UrlLogEntity dto2Entity(UrlLogDto dto);

}
