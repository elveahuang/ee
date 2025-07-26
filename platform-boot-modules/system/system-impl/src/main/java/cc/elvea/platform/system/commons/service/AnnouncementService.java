package cc.elvea.platform.system.commons.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.commons.model.entity.AnnouncementEntity;
import cc.elvea.platform.system.commons.model.form.AnnouncementForm;
import cc.elvea.platform.system.commons.model.request.AnnouncementSearchRequest;
import cc.elvea.platform.system.commons.model.request.SystemAnnouncementSearchRequest;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 */
public interface AnnouncementService extends CachingEntityService<AnnouncementEntity, Long> {

    /**
     * 前端分页查询
     */
    Page<?> findByPage(AnnouncementSearchRequest request);

    /**
     * 后端分页查询
     */
    Page<?> findByPage(SystemAnnouncementSearchRequest request);

    /**
     * 保存公告
     */
    void saveAnnouncement(AnnouncementForm form);

}
