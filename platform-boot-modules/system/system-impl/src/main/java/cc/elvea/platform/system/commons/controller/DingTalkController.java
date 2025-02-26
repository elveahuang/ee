package cc.elvea.platform.system.commons.controller;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.oapis.dingtalk.bean.JsapiSignature;
import cc.elvea.platform.commons.oapis.dingtalk.service.DingTalkService;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.commons.constants.SystemMappingConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@Slf4j
@RestController
@Tag(name = "DingTalkController", description = "钉钉控制器")
public class DingTalkController extends AbstractController {

    private DingTalkService dingTalkService;

    @PermitAll
    @OperationLog("获取钉钉签名")
    @Operation(summary = "获取钉钉签名")
    @ApiResponse(description = "获取钉钉签名")
    @ResponseBody
    @GetMapping(SystemMappingConstants.API_V1__DINGTALK__SIGNATURE)
    public R<JsapiSignature> getSignature(@RequestBody String url) throws Exception {
        log.info("get feishu signature for url [{}]", url);
        return R.success(this.dingTalkService.createJsapiSignature(url));
    }

    @PermitAll
    @OperationLog("钉钉回调接口")
    @Operation(summary = "钉钉回调接口")
    @ApiResponse(description = "钉钉回调接口")
    @GetMapping(SystemMappingConstants.API_V1__DINGTALK__CALLBACK)
    public R<?> callback() {
        return R.success();
    }

    @Autowired(required = false)
    public void setDingTalkService(DingTalkService dingTalkService) {
        this.dingTalkService = dingTalkService;
    }

}
