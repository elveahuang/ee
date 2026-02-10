package cc.wdev.platform.system.message.controller.app;

import cc.wdev.platform.commons.annotations.Authenticated;
import cc.wdev.platform.commons.annotations.OperationLog;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.SecurityUtils;
import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import cc.wdev.platform.system.message.domain.entity.NoticeEntity;
import cc.wdev.platform.system.message.domain.request.NoticeSearchRequest;
import cc.wdev.platform.system.message.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1__NOTICE__DETAILS;
import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1__NOTICE__LIST;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "NoticeController", description = "系统通知控制器")
public class NoticeController extends AbstractController {

    private final NoticeService noticeService;

    @Authenticated
    @Operation(summary = "获取系统通知列表")
    @ApiResponse(description = "获取系统通知列表")
    @PostMapping(API_V1__NOTICE__LIST)
    @OperationLog("获取系统通知列表")
    public R<?> list(NoticeSearchRequest request) {
        return R.success(noticeService.findMyNoticeByUserId(request));
    }

    @Authenticated
    @Operation(summary = "获取系统通知详情")
    @ApiResponse(description = "获取系统通知详情")
    @PostMapping(API_V1__NOTICE__DETAILS)
    @OperationLog("获取系统通知详情")
    public R<NoticeEntity> details(@RequestParam Long id) {
        NoticeEntity entity = noticeService.findById(id);
        if (entity != null && entity.getRecipientId().longValue() == SecurityUtils.getUid()) {
            return R.success(entity);
        }
        return R.error();
    }

}
