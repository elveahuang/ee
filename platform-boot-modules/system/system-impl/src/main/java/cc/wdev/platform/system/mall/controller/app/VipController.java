package cc.wdev.platform.system.mall.controller.app;

import cc.wdev.platform.commons.annotations.Authenticated;
import cc.wdev.platform.commons.annotations.OperationLog;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import cc.wdev.platform.system.mall.api.VipApi;
import cc.wdev.platform.system.mall.domain.vo.VipTypeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1__VIP__TYPE;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "VipController", description = "会员控制器")
public class VipController extends AbstractController {

    private final VipApi vipApi;

    @Authenticated
    @Operation(summary = "获取当前会员类型")
    @ApiResponse(description = "获取当前会员类型")
    @OperationLog("获取当前会员类型")
    @PostMapping(API_V1__VIP__TYPE)
    public R<List<VipTypeVo>> typeList() {
        return R.success(this.vipApi.getTypeList());
    }

}
