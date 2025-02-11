package cc.elvea.platform.system.dict.controller.admin;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.dict.model.entity.DictTypeEntity;
import cc.elvea.platform.system.dict.model.request.DictTypeSearchRequest;
import cc.elvea.platform.system.dict.service.DictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__DICT__TYPE_LIST;

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
