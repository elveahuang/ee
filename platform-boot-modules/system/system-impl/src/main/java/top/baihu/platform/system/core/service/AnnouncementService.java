package top.baihu.platform.system.core.service;

import org.springframework.data.domain.Page;
import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.AnnouncementEntity;
import top.baihu.platform.system.core.domain.form.AnnouncementForm;
import top.baihu.platform.system.core.domain.request.AnnouncementSearchRequest;
import top.baihu.platform.system.core.domain.request.SystemAnnouncementSearchRequest;

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
