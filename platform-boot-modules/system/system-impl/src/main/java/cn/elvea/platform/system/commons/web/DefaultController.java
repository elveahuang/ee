package cn.elvea.platform.system.commons.web;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.constants.GlobalConstants;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.commons.model.vo.InitializeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_HOME;
import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_INITIALIZE;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@Tag(name = "DefaultController", description = "默认控制器")
public class DefaultController {

    @PermitAll
    @OperationLog("获取应用初始数据")
    @Operation(summary = "获取应用初始数据")
    @ApiResponse(description = "获取应用初始数据")
    @GetMapping(API_V1_INITIALIZE)
    public R<InitializeVo> initialize() {
        return R.success(InitializeVo.builder()
                .version(GlobalConstants.VERSION)
                .now(LocalDateTime.now()).build());
    }

    @PermitAll
    @OperationLog("首页")
    @Operation(summary = "首页")
    @ApiResponse(description = "首页")
    @GetMapping(API_V1_HOME)
    public R<InitializeVo> home() {
        return R.success(InitializeVo.builder()
                .version(GlobalConstants.VERSION)
                .now(LocalDateTime.now()).build());
    }

}
