package top.baihu.platform.system.core.web.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.annotations.Authenticated;
import top.baihu.platform.commons.annotations.OperationLog;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.web.controller.AbstractController;
import top.baihu.platform.system.core.domain.request.DictSearchRequest;
import top.baihu.platform.system.core.domain.request.DictTypeRequest;
import top.baihu.platform.system.core.domain.vo.DictItemVo;
import top.baihu.platform.system.core.domain.vo.DictTypeVo;
import top.baihu.platform.system.core.manager.DictManager;

import static top.baihu.platform.system.commons.constants.SystemMappingConstants.API_V1__DICT__SEARCH;
import static top.baihu.platform.system.commons.constants.SystemMappingConstants.API_V1__DICT__TYPE;

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
