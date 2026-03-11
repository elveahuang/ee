package cc.wdev.platform.system.mall.controller.app;

import cc.wdev.platform.commons.annotations.Authenticated;
import cc.wdev.platform.commons.annotations.OperationLog;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import cc.wdev.platform.system.mall.api.PayApi;
import cc.wdev.platform.system.mall.domain.vo.PayTypeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1__PAY__TYPE;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "PayController", description = "支付控制器")
public class PayController extends AbstractController {

    private final PayApi payApi;

    @Authenticated
    @Operation(summary = "获取当前可用支付方式")
    @ApiResponse(description = "获取当前可用支付方式")
    @OperationLog("获取当前可用支付方式")
    @PostMapping(API_V1__PAY__TYPE)
    public R<List<PayTypeVo>> payTypeList() {
        return R.success(this.payApi.getPayTypeList());
    }

}
