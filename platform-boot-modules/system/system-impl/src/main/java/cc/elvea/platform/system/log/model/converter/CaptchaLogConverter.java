package cc.elvea.platform.system.log.model.converter;

import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.elvea.platform.system.log.model.entity.CaptchaLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface CaptchaLogConverter {

    CaptchaLogConverter INSTANCE = Mappers.getMapper(CaptchaLogConverter.class);

    CaptchaLogEntity dto2Entity(CaptchaLogDto dto);

}
