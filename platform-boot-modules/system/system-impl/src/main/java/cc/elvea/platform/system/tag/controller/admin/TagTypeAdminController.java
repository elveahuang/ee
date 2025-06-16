package cc.elvea.platform.system.tag.controller.admin;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.tag.model.entity.TagTypeEntity;
import cc.elvea.platform.system.tag.model.request.TagTypeSearchRequest;
import cc.elvea.platform.system.tag.service.TagTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__TAG__TYPE_LIST;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "SystemTagTypeController", description = "标签类型系统控制器")
public class TagTypeAdminController extends AbstractController {

    private final TagTypeService tagTypeService;

    @Authenticated
    @Operation(summary = "获取标签类型列表")
    @ApiResponse(description = "获取标签类型列表")
    @OperationLog("获取标签类型列表")
    @PostMapping(API_V1_ADMIN__TAG__TYPE_LIST)
    public R<Page<TagTypeEntity>> list(TagTypeSearchRequest request) {
        return R.success(this.tagTypeService.findByPage(request.getPageable()));
    }

}
