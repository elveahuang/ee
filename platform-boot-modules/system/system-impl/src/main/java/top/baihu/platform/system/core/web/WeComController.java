package top.baihu.platform.system.core.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.annotations.OperationLog;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.web.controller.AbstractController;
import top.baihu.platform.system.commons.constants.SystemMappingConstants;

/**
 * @author elvea
 */
@RestController
@Tag(name = "WeComController", description = "企业微信控制器")
public class WeComController extends AbstractController {

    @PermitAll
    @OperationLog("获取企业微信签名")
    @Operation(summary = "获取企业微信签名")
    @ApiResponse(description = "获取企业微信签名")
    @ResponseBody
    @GetMapping(SystemMappingConstants.API_V1__WECOM__SIGNATURE)
    public R<?> getSignature(@RequestBody String url) {
        return R.success();
    }

    @PermitAll
    @OperationLog("企业微信回调接口")
    @Operation(summary = "企业微信回调接口")
    @ApiResponse(description = "企业微信回调接口")
    @GetMapping(SystemMappingConstants.API_V1__WECOM__CALLBACK)
    public R<?> callback() {
        return R.success();
    }

}
