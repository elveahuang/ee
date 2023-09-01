package cn.elvea.platform.system.core.model.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface AuthorizationConverter {

    AuthorizationConverter INSTANCE = Mappers.getMapper(AuthorizationConverter.class);

}
