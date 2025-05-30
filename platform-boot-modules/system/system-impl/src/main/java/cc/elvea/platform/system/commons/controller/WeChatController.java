package cc.elvea.platform.system.commons.controller;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.commons.constants.SystemMappingConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@RestController
@Tag(name = "WeChatController", description = "微信控制器")
public class WeChatController extends AbstractController {

    @PermitAll
    @OperationLog("获取微信签名")
    @Operation(summary = "获取微信签名")
    @ApiResponse(description = "获取微信签名")
    @ResponseBody
    @GetMapping(SystemMappingConstants.API_V1__WECHAT__SIGNATURE)
    public R<?> getSignature(@RequestBody String url) {
        return R.success();
    }

    @PermitAll
    @OperationLog("微信回调接口")
    @Operation(summary = "微信回调接口")
    @ApiResponse(description = "微信回调接口")
    @GetMapping(SystemMappingConstants.API_V1__WECHAT__CALLBACK)
    public R<?> callback() {
        return R.success();
    }

}
