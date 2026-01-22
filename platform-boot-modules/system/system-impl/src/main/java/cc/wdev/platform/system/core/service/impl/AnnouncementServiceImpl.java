package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.enums.ActiveTypeEnum;
import cc.wdev.platform.commons.enums.StatusTypeEnum;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.core.domain.converter.AnnouncementConverter;
import cc.wdev.platform.system.core.domain.entity.AnnouncementEntity;
import cc.wdev.platform.system.core.domain.form.AnnouncementForm;
import cc.wdev.platform.system.core.domain.request.AnnouncementSearchRequest;
import cc.wdev.platform.system.core.domain.request.SystemAnnouncementSearchRequest;
import cc.wdev.platform.system.core.repository.AnnouncementRepository;
import cc.wdev.platform.system.core.service.AnnouncementService;
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
        example.setActive(ActiveTypeEnum.ENABLED.getValue());
        return findByPage(request.getPageable(), example);
    }

    /**
     * @see AnnouncementService#saveAnnouncement(AnnouncementForm)
     */
    @Override
    public Page<?> findByPage(AnnouncementSearchRequest request) {
        AnnouncementEntity example = AnnouncementEntity.builder().build();
        example.setActive(ActiveTypeEnum.ENABLED.getValue());
        example.setStatus(StatusTypeEnum.ON.getValue());
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
        entity.setActive(ActiveTypeEnum.ENABLED.getValue());
        this.save(entity);
    }

}
