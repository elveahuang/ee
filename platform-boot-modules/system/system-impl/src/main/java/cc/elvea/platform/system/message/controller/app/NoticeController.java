package cc.elvea.platform.system.message.controller.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.utils.SecurityUtils;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.message.model.entity.NoticeEntity;
import cc.elvea.platform.system.message.request.NoticeSearchRequest;
import cc.elvea.platform.system.message.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__NOTICE__DETAILS;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__NOTICE__LIST;

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
