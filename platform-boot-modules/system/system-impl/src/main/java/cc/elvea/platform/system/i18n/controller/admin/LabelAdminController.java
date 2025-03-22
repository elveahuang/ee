package cc.elvea.platform.system.i18n.controller.admin;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__LABEL__GENERATE;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__LABEL__TRANSLATE;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
@Tag(name = "LabelAdminController", description = "多语言后台控制器")
public class LabelAdminController extends AbstractController {

    @OperationLog("后台管理-翻译多语言文本")
    @Operation(summary = "后台管理-翻译多语言文本")
    @ApiResponse(description = "后台管理-翻译多语言文本")
    @GetMapping(API_V1_ADMIN__LABEL__TRANSLATE)
    public R<?> translate() {
        return R.success();
    }

    @OperationLog("后台管理-生成多语言文件")
    @Operation(summary = "后台管理-生成多语言文件")
    @ApiResponse(description = "后台管理-生成多语言文件")
    @GetMapping(API_V1_ADMIN__LABEL__GENERATE)
    public R<?> generate() {
        return R.success();
    }

}
