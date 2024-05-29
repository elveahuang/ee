package cc.elvea.platform.system.core.model.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface AuthorizationConverter {

    AuthorizationConverter INSTANCE = Mappers.getMapper(AuthorizationConverter.class);

}
