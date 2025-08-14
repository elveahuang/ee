package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import top.baihu.platform.system.core.domain.entity.CaptchaLogEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface CaptchaLogConverter {

    CaptchaLogEntity dto2Entity(CaptchaLogDto dto);

}
