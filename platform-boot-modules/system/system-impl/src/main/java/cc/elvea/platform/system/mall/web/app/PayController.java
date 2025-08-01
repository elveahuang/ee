package cc.elvea.platform.system.mall.web.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.mall.domain.vo.PayTypeVo;
import cc.elvea.platform.system.mall.manager.PayManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__PAY__TYPE;

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
