package top.baihu.platform.system.core.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.web.request.PageRequest;
import top.baihu.platform.system.core.service.AnnouncementService;

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
