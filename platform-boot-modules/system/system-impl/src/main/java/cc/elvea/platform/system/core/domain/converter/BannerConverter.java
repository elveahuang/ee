package cc.elvea.platform.system.core.domain.converter;

import cc.elvea.platform.system.core.domain.entity.BannerEntity;
import cc.elvea.platform.system.core.domain.form.BannerForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface BannerConverter {

    @Mapping(target = "details", ignore = true)
    BannerEntity formToEntity(BannerForm form);

}
