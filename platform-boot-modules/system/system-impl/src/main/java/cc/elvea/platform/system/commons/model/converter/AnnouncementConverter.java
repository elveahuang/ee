package cc.elvea.platform.system.commons.model.converter;

import cc.elvea.platform.system.commons.model.entity.AnnouncementEntity;
import cc.elvea.platform.system.commons.model.form.AnnouncementForm;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AnnouncementConverter {

    AnnouncementEntity formToEntity(AnnouncementForm form);

}
