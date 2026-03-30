package cc.wdev.platform.system.core.controller.admin;

import cc.wdev.platform.commons.annotations.Authenticated;
import cc.wdev.platform.commons.annotations.OperationLog;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import cc.wdev.platform.system.core.domain.entity.DictTypeEntity;
import cc.wdev.platform.system.core.domain.request.DictTypeSearchRequest;
import cc.wdev.platform.system.core.service.DictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__DICT__TYPE_LIST;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "DictTypeAdminController", description = "字典类型后台管理控制器")
public class DictTypeAdminController extends AbstractController {

    private final DictTypeService dictTypeService;

    @Authenticated
    @Operation(summary = "获取字典类型列表")
    @ApiResponse(description = "获取字典类型列表")
    @OperationLog("获取字典类型列表")
    @PostMapping(API_V1_ADMIN__DICT__TYPE_LIST)
    public R<Page<DictTypeEntity>> list(DictTypeSearchRequest request) {
        return R.success(this.dictTypeService.findByPage(request.getPageable()));
    }

}
