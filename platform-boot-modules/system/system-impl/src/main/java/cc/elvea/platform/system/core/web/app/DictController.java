package cc.elvea.platform.system.core.web.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.core.domain.request.DictSearchRequest;
import cc.elvea.platform.system.core.domain.request.DictTypeRequest;
import cc.elvea.platform.system.core.domain.vo.DictItemVo;
import cc.elvea.platform.system.core.domain.vo.DictTypeVo;
import cc.elvea.platform.system.core.manager.DictManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__DICT__SEARCH;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__DICT__TYPE;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "DictController", description = "字典控制器")
public class DictController extends AbstractController {

    private final DictManager dictManager;

    @Authenticated
    @Operation(summary = "获取字典类型")
    @ApiResponse(description = "获取字典类型")
    @OperationLog("获取字典类型")
    @PostMapping(API_V1__DICT__TYPE)
    public R<DictTypeVo> type(DictTypeRequest request) {
        return R.success(dictManager.getDictType(request));
    }

    @Authenticated
    @Operation(summary = "搜索字典项")
    @ApiResponse(description = "搜索字典项")
    @OperationLog("搜索字典项")
    @PostMapping(API_V1__DICT__SEARCH)
    public R<Page<DictItemVo>> search(DictSearchRequest request) {
        return R.success(dictManager.search(request));
    }

}
