package cc.elvea.platform.system.commons.controller;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.oapis.lark.bean.JsapiSignature;
import cc.elvea.platform.commons.oapis.lark.service.LarkService;
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
@Tag(name = "LarkController", description = "飞书控制器")
public class LarkController extends AbstractController {

    private LarkService larkService;

    @PermitAll
    @OperationLog("获取飞书签名")
    @Operation(summary = "获取飞书签名")
    @ApiResponse(description = "获取飞书签名")
    @ResponseBody
    @GetMapping(SystemMappingConstants.API_V1__LARK__SIGNATURE)
    public R<JsapiSignature> getSignature(@RequestBody String url) throws Exception {
        log.info("get feishu signature for url [{}]", url);
        return R.success(this.larkService.createJsapiSignature(url));
    }

    @PermitAll
    @OperationLog("飞书回调接口")
    @Operation(summary = "飞书回调接口")
    @ApiResponse(description = "飞书回调接口")
    @GetMapping(SystemMappingConstants.API_V1__LARK__CALLBACK)
    public R<?> callback() {
        return R.success();
    }

    @Autowired(required = false)
    public void setLarkService(LarkService larkService) {
        this.larkService = larkService;
    }

}
