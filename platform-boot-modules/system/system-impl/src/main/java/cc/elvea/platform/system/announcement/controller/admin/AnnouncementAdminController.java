package cc.elvea.platform.system.announcement.controller.admin;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cc.elvea.platform.system.announcement.model.form.AnnouncementForm;
import cc.elvea.platform.system.announcement.model.request.SystemAnnouncementDeleteRequest;
import cc.elvea.platform.system.announcement.model.request.SystemAnnouncementSearchRequest;
import cc.elvea.platform.system.announcement.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "AnnouncementAdminController", description = "公告资讯后台管理控制器")
public class AnnouncementAdminController extends AbstractController {

    private final AnnouncementService announcementService;

    @OperationLog("获取公告资讯列表")
    @Operation(summary = "获取公告资讯列表")
    @ApiResponse(description = "获取公告资讯列表")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__LIST)
    public R<Page<?>> list(SystemAnnouncementSearchRequest request) {
        return R.success(announcementService.findByPage(request));
    }

    @OperationLog("获取公告资讯详情")
    @Operation(summary = "获取公告资讯详情")
    @ApiResponse(description = "获取公告资讯详情")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__DETAILS)
    public R<AnnouncementEntity> details(@RequestParam("id") Long id) {
        return R.success(announcementService.findById(id));
    }

    @OperationLog("准备公告资讯")
    @Operation(summary = "准备公告资讯")
    @ApiResponse(description = "准备公告资讯")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__PREPARE)
    public R<AnnouncementEntity> prepare(@RequestParam Long id) {
        return R.success(announcementService.findById(id));
    }

    @OperationLog("保存公告资讯")
    @Operation(summary = "保存公告资讯")
    @ApiResponse(description = "保存公告资讯")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__SAVE)
    public R<?> save(@Valid AnnouncementForm form) {
        this.announcementService.saveAnnouncement(form);
        return R.success();
    }

    @OperationLog("删除公告资讯")
    @Operation(summary = "删除公告资讯")
    @ApiResponse(description = "删除公告资讯")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__DELETE)
    public R<?> delete(SystemAnnouncementDeleteRequest request) {
        if (request != null && request.getIds() != null && request.getIds().length > 0) {
            announcementService.softDeleteBatchById(Arrays.asList(request.getIds()));
        }
        return R.success();
    }

}
