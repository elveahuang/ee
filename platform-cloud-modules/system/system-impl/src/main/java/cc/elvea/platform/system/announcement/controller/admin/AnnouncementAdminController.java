package cc.elvea.platform.system.announcement.controller.admin;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.request.PageRequest;
import cc.elvea.platform.system.announcement.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@RestController
@RequestMapping("/api/admin/v1/announcement")
@Tag(name = "AnnouncementMgrController", description = "公告管理控制器")
public class AnnouncementAdminController {

    private final AnnouncementService announcementService;

    public AnnouncementAdminController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @Operation(summary = "公告列表")
    @ApiResponse(description = "公告列表")
    @PostMapping(value = "search")
    public R<?> search(PageRequest pageRequest) {
        return R.success(announcementService.findByPage(pageRequest.getPageable()));
    }

}
