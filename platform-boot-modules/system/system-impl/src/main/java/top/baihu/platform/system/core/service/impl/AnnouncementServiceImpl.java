package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.enums.PublishStatusTypeEnum;
import top.baihu.platform.commons.utils.ObjectUtils;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.core.domain.converter.AnnouncementConverter;
import top.baihu.platform.system.core.domain.entity.AnnouncementEntity;
import top.baihu.platform.system.core.domain.form.AnnouncementForm;
import top.baihu.platform.system.core.domain.request.AnnouncementSearchRequest;
import top.baihu.platform.system.core.domain.request.SystemAnnouncementSearchRequest;
import top.baihu.platform.system.core.repository.AnnouncementRepository;
import top.baihu.platform.system.core.service.AnnouncementService;

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
