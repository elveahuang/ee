package cc.elvea.platform.system.security.model.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface AuthorizationConsentConverter {

    AuthorizationConsentConverter INSTANCE = Mappers.getMapper(AuthorizationConsentConverter.class);

}
