package cc.elvea.platform.system.core.web.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.core.domain.request.TagSearchRequest;
import cc.elvea.platform.system.core.domain.request.TagTypeRequest;
import cc.elvea.platform.system.core.domain.vo.TagTypeVo;
import cc.elvea.platform.system.core.domain.vo.TagVo;
import cc.elvea.platform.system.core.manager.TagManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__TAG__SEARCH;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__TAG__TYPE;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "TagController", description = "标签控制器")
public class TagController extends AbstractController {

    private final TagManager tagManager;

    @Authenticated
    @Operation(summary = "获取标签类型")
    @ApiResponse(description = "获取标签类型")
    @OperationLog("获取标签类型")
    @PostMapping(API_V1__TAG__TYPE)
    public R<TagTypeVo> type(TagTypeRequest request) {
        return R.success(tagManager.getTagType(request));
    }

    @Authenticated
    @Operation(summary = "搜索标签")
    @ApiResponse(description = "搜索标签")
    @OperationLog("搜索标签")
    @PostMapping(API_V1__TAG__SEARCH)
    public R<Page<TagVo>> search(TagSearchRequest request) {
        return R.success(tagManager.search(request));
    }

}
