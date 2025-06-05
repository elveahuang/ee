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

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "OrderAdminController", description = "订单后台控制器")
public class OrderAdminController extends AbstractController {

    @Authenticated
    @Operation(summary = "订单列表")
    @ApiResponse(description = "订单列表")
    @OperationLog("订单列表")
    @PostMapping(API_V1_ADMIN__ORDER__LIST)
    public R<?> list() {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "订单详情")
    @ApiResponse(description = "订单详情")
    @OperationLog("订单详情")
    @PostMapping(API_V1_ADMIN__ORDER__DETAILS)
    public R<?> details() {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "保存订单")
    @ApiResponse(description = "保存订单")
    @OperationLog("删除订单")
    @PostMapping(API_V1_ADMIN__ORDER__SAVE)
    public R<?> save() {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "删除订单")
    @ApiResponse(description = "删除订单")
    @OperationLog("删除订单")
    @PostMapping(API_V1_ADMIN__ORDER__DELETE)
    public R<?> delete() {
        return R.success();
    }

}
