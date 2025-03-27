package cc.elvea.platform.system.announcement.service;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.enums.PublishStatusTypeEnum;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.announcement.model.converter.AnnouncementConverter;
import cc.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cc.elvea.platform.system.announcement.model.form.AnnouncementForm;
import cc.elvea.platform.system.announcement.model.request.AnnouncementSearchRequest;
import cc.elvea.platform.system.announcement.model.request.SystemAnnouncementSearchRequest;
import cc.elvea.platform.system.announcement.repository.AnnouncementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class AnnouncementServiceImpl
        extends BaseCachingEntityService<AnnouncementEntity, Long, AnnouncementRepository>
        implements AnnouncementService {

    /**
     * @see AnnouncementService#saveAnnouncement(AnnouncementForm)
     */
    @Override
    public Page<?> findByPage(SystemAnnouncementSearchRequest request) {
        AnnouncementEntity example = AnnouncementEntity.builder().build();
        example.setActive(Boolean.TRUE);
        return findByPage(request.getPageable(), example);
    }

    /**
     * @see AnnouncementService#saveAnnouncement(AnnouncementForm)
     */
    @Override
    public Page<?> findByPage(AnnouncementSearchRequest request) {
        AnnouncementEntity example = AnnouncementEntity.builder().build();
        example.setActive(Boolean.TRUE);
        example.setStatus(PublishStatusTypeEnum.ON.getValue());
        return findByPage(request.getPageable(), example);
    }

    /**
     * @see AnnouncementService#saveAnnouncement(AnnouncementForm)
     */
    @Override
    public void saveAnnouncement(AnnouncementForm form) {
        AnnouncementEntity entity;
        if (form.getId() != null && form.getId() > 0) {
            entity = this.findById(form.getId());
            ObjectUtils.copyNotNullProperties(form, entity);
        } else {
            entity = SpringUtils.getBean(AnnouncementConverter.class).formToEntity(form);
        }
        entity.setActive(Boolean.TRUE);
        this.save(entity);
    }

}
