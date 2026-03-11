package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.AnnouncementEntity;
import cc.wdev.platform.system.core.domain.form.AnnouncementForm;
import cc.wdev.platform.system.core.domain.request.AnnouncementSearchRequest;
import cc.wdev.platform.system.core.domain.request.SystemAnnouncementSearchRequest;
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
