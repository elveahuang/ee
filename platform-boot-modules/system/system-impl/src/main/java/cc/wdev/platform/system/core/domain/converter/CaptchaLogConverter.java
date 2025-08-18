package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.wdev.platform.system.core.domain.entity.CaptchaLogEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface CaptchaLogConverter {

    CaptchaLogEntity dto2Entity(CaptchaLogDto dto);

}
