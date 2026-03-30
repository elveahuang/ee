package cc.wdev.platform.system.mall.controller.admin;

import cc.wdev.platform.commons.annotations.Authenticated;
import cc.wdev.platform.commons.annotations.OperationLog;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__VIP__LIST;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "VipAdminController", description = "会员后台控制器")
public class VipAdminController extends AbstractController {

    @Authenticated
    @Operation(summary = "会员类型列表")
    @ApiResponse(description = "会员类型列表")
    @OperationLog("会员类型列表")
    @PostMapping(API_V1_ADMIN__VIP__LIST)
    public R<?> list() {
        return R.success();
    }

}
