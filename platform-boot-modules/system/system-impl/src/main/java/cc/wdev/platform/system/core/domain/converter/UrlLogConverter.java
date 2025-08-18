package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.commons.core.log.domain.UrlLogDto;
import cc.wdev.platform.system.core.domain.entity.UrlLogEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface UrlLogConverter {

    UrlLogEntity dto2Entity(UrlLogDto dto);

}
