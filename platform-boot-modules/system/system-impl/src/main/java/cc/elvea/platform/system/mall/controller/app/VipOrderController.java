package cc.elvea.platform.system.mall.controller.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.mall.api.OrderApi;
import cc.elvea.platform.system.mall.api.VipApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__VIP__ORDER__CONFIRM;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "VipController", description = "会员控制器")
public class VipOrderController extends AbstractController {

    private final VipApi vipApi;

    private final OrderApi orderApi;

    @Authenticated
    @Operation(summary = "确认订单")
    @ApiResponse(description = "确认订单")
    @OperationLog("确认订单")
    @PostMapping(API_V1__VIP__ORDER__CONFIRM)
    public R<?> confirm(@Parameter(name = "会员类型") @RequestParam("vipTypeId") Long vipTypeId,
                        @Parameter(name = "会员套餐") @RequestParam("vipItemId") Long vipItemId) {
        return R.success();
    }

}
