package cn.elvea.platform.system.security.model.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface AuthorizationConsentConverter {

    AuthorizationConsentConverter INSTANCE = Mappers.getMapper(AuthorizationConsentConverter.class);

}
