package cc.elvea.platform.system.core.web;

import cc.elvea.platform.commons.annotations.Anonymous;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.system.core.domain.vo.InitializeVo;
import cc.elvea.platform.system.core.domain.vo.VersionVo;
import cc.elvea.platform.system.core.manager.CoreManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "DefaultController", description = "默认控制器")
public class DefaultController {

    private final CoreManager coreManager;

    @Anonymous
    @OperationLog("获取应用初始数据")
    @Operation(summary = "获取应用初始数据")
    @ApiResponse(description = "获取应用初始数据")
    @PostMapping(API_V1_INITIALIZE)
    public R<InitializeVo> initialize() {
        return R.success(coreManager.initialize());
    }

    @Anonymous
    @OperationLog("获取应用版本号")
    @Operation(summary = "获取应用版本号")
    @ApiResponse(description = "获取应用版本号")
    @PostMapping(API_V1_VERSION)
    public R<VersionVo> version() {
        return R.success(VersionVo.builder().build());
    }

    @Anonymous
    @OperationLog("首页")
    @Operation(summary = "首页")
    @ApiResponse(description = "首页")
    @PostMapping(API_V1_HOME)
    public R<InitializeVo> home() {
        return R.success(coreManager.initialize());
    }

}
