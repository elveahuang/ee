package cc.elvea.platform.system.commons.controller.admin;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.commons.web.response.R;
import cc.elvea.platform.system.commons.manager.TagManager;
import cc.elvea.platform.system.commons.model.entity.TagEntity;
import cc.elvea.platform.system.commons.model.form.TagForm;
import cc.elvea.platform.system.commons.model.request.TagSearchRequest;
import cc.elvea.platform.system.commons.service.TagService;
import cc.elvea.platform.system.commons.service.TagTypeService;
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
@Tag(name = "SystemTagController", description = "标签系统控制器")
public class TagAdminController extends AbstractController {

    private final TagManager tagManager;

    private final TagService tagService;

    private final TagTypeService tagTypeService;

    @Authenticated
    @Operation(summary = "获取指定类型的标签列表")
    @ApiResponse(description = "获取字典类型列表")
    @OperationLog("获取字典类型列表")
    @PostMapping(API_V1_ADMIN__TAG__LIST)
    public R<Page<TagEntity>> list(TagSearchRequest request) {
        return R.success(this.tagService.findByPage(request.getPageable()));
    }

    @Authenticated
    @Operation(summary = "获取标签详情")
    @ApiResponse(description = "获取标签详情")
    @OperationLog("获取标签详情")
    @PostMapping(API_V1_ADMIN__TAG__DETAILS)
    public R<Page<TagEntity>> details(@RequestParam("id") Long id) {
        return R.success();
    }

    @Authenticated
    @Operation(summary = "保存标签")
    @ApiResponse(description = "保存标签")
    @OperationLog("保存标签")
    @PostMapping(API_V1_ADMIN__TAG__SAVE)
    public R<Page<TagEntity>> save(TagForm form) {
        tagManager.save(form);
        return R.success();
    }

    @Authenticated
    @Operation(summary = "删除标签")
    @ApiResponse(description = "删除标签")
    @OperationLog("删除标签")
    @PostMapping(API_V1_ADMIN__TAG__DELETE)
    public R<Page<TagEntity>> delete(@RequestParam("id") Long id) {
        return R.success();
    }

}
