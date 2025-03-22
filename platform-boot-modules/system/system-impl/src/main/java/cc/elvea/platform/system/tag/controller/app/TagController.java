package cc.elvea.platform.system.tag.controller.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.tag.api.TagApi;
import cc.elvea.platform.system.tag.model.request.TagSearchRequest;
import cc.elvea.platform.system.tag.model.request.TagTypeRequest;
import cc.elvea.platform.system.tag.model.vo.TagTypeVo;
import cc.elvea.platform.system.tag.model.vo.TagVo;
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

    private final TagApi tagApi;

    @Authenticated
    @Operation(summary = "获取标签类型")
    @ApiResponse(description = "获取标签类型")
    @OperationLog("获取标签类型")
    @PostMapping(API_V1__TAG__TYPE)
    public R<TagTypeVo> type(TagTypeRequest request) {
        return R.success(tagApi.getTagType(request));
    }

    @Authenticated
    @Operation(summary = "搜索标签")
    @ApiResponse(description = "搜索标签")
    @OperationLog("搜索标签")
    @PostMapping(API_V1__TAG__SEARCH)
    public R<Page<TagVo>> search(TagSearchRequest request) {
        return R.success(tagApi.search(request));
    }

}
