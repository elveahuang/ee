package cc.elvea.platform.system.core.web.admin;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.core.domain.entity.DictItemEntity;
import cc.elvea.platform.system.core.domain.entity.TagEntity;
import cc.elvea.platform.system.core.domain.form.DictForm;
import cc.elvea.platform.system.core.domain.request.DictSearchRequest;
import cc.elvea.platform.system.core.manager.DictManager;
import cc.elvea.platform.system.core.service.DictItemService;
import cc.elvea.platform.system.core.service.DictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "DictAdminController", description = "字典后台管理控制器")
public class DictAdminController extends AbstractController {

    private final DictManager dictManager;

    private final DictItemService dictItemService;

    private final DictTypeService dictTypeService;

    @Authenticated
    @Operation(summary = "获取指定类型字典项")
    @ApiResponse(description = "获取指定类型字典项")
    @OperationLog("获取指定类型字典项")
    @PostMapping(API_V1_ADMIN__DICT__LIST)
    public R<Page<DictItemEntity>> list(DictSearchRequest request) {
        return R.success(this.dictItemService.findByPage(request.getPageable()));
    }

    @Authenticated
    @Operation(summary = "获取标签详情")
    @ApiResponse(description = "获取标签详情")
    @OperationLog("获取标签详情")
    @PostMapping(API_V1_ADMIN__DICT__DETAILS)
    public R<Page<TagEntity>> details(@RequestParam("id") Long id) {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "保存标签")
    @ApiResponse(description = "保存标签")
    @OperationLog("保存标签")
    @PostMapping(API_V1_ADMIN__DICT__SAVE)
    public R<?> save(DictForm form) {
        dictManager.save(form);
        return R.success();
    }

    @Authenticated
    @Operation(summary = "删除标签")
    @ApiResponse(description = "删除标签")
    @OperationLog("删除标签")
    @PostMapping(API_V1_ADMIN__DICT__DELETE)
    public R<Page<TagEntity>> delete(@RequestParam("id") Long id) {
        return R.success();
    }

}
