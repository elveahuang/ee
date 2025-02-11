package cc.elvea.platform.system.mall.controller.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.mall.api.VipApi;
import cc.elvea.platform.system.mall.model.vo.VipTypeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__VIP__TYPE;

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
