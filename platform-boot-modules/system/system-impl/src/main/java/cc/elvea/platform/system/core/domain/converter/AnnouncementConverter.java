package cc.elvea.platform.system.core.domain.converter;

import cc.elvea.platform.system.core.domain.entity.AnnouncementEntity;
import cc.elvea.platform.system.core.domain.form.AnnouncementForm;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AnnouncementConverter {

    AnnouncementEntity formToEntity(AnnouncementForm form);

}
