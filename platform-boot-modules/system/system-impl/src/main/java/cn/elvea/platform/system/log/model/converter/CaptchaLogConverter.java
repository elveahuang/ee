package cn.elvea.platform.system.log.model.converter;

import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.system.log.model.entity.CaptchaLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface CaptchaLogConverter {

    CaptchaLogConverter INSTANCE = Mappers.getMapper(CaptchaLogConverter.class);

    CaptchaLogEntity dto2Entity(CaptchaLogDto dto);

}
