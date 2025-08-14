package top.baihu.platform.system.mall.web.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.annotations.Authenticated;
import top.baihu.platform.commons.annotations.OperationLog;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.web.controller.AbstractController;
import top.baihu.platform.system.mall.domain.vo.PayTypeVo;
import top.baihu.platform.system.mall.manager.PayManager;

import java.util.List;

import static top.baihu.platform.system.commons.constants.SystemMappingConstants.API_V1__PAY__TYPE;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "PayController", description = "支付控制器")
public class PayController extends AbstractController {

    private final PayManager payManager;

    @Authenticated
    @Operation(summary = "获取当前可用支付方式")
    @ApiResponse(description = "获取当前可用支付方式")
    @OperationLog("获取当前可用支付方式")
    @PostMapping(API_V1__PAY__TYPE)
    public R<List<PayTypeVo>> payTypeList() {
        return R.success(this.payManager.getPayTypeList());
    }

}
