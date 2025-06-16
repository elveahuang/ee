package cc.elvea.platform.system.mall.controller.admin;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__VIP__LIST;

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
