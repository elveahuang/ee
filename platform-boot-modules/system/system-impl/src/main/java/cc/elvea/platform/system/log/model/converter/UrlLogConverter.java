package cc.elvea.platform.system.log.model.converter;

import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.system.log.model.entity.UrlLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface UrlLogConverter {

    UrlLogConverter INSTANCE = Mappers.getMapper(UrlLogConverter.class);

    UrlLogEntity dto2Entity(UrlLogDto dto);

}
