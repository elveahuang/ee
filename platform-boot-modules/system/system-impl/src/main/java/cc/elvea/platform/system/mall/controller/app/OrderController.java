package cc.elvea.platform.system.mall.controller.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "OrderController", description = "订单控制器")
public class OrderController extends AbstractController {

    @Authenticated
    @Operation(summary = "确认订单")
    @ApiResponse(description = "确认订单")
    @OperationLog("确认订单")
    @PostMapping(API_V1__ORDER__CONFIRM)
    public R<?> confirm(@RequestParam("itemId") Long itemId) {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "提交订单")
    @ApiResponse(description = "提交订单")
    @OperationLog("提交订单")
    @PostMapping(API_V1__ORDER__SUBMIT)
    public R<?> submit() {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "我的订单")
    @ApiResponse(description = "我的订单")
    @OperationLog("我的订单")
    @PostMapping(API_V1__ORDER__LIST)
    public R<?> list() {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "我的订单详情")
    @ApiResponse(description = "我的订单详情")
    @OperationLog("我的订单详情")
    @PostMapping(API_V1__ORDER__DETAILS)
    public R<?> details() {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "取消我的订单")
    @ApiResponse(description = "取消我的订单")
    @OperationLog("取消我的订单")
    @PostMapping(API_V1__ORDER__CANCEL)
    public R<?> cancel() {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "删除我的订单")
    @ApiResponse(description = "删除我的订单")
    @OperationLog("删除我的订单")
    @PostMapping(API_V1__ORDER__DELETE)
    public R<?> delete() {
        return R.success();
    }

}
