package cc.elvea.platform.system.banner.model.converter;

import cc.elvea.platform.system.banner.model.entity.BannerEntity;
import cc.elvea.platform.system.banner.model.form.BannerForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface BannerConverter {

    BannerConverter INSTANCE = Mappers.getMapper(BannerConverter.class);

    @Mapping(target = "details", ignore = true)
    BannerEntity formToEntity(BannerForm form);

}
