package cn.elvea.platform.system.core.controller.admin;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.request.PageRequest;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__USER__LIST;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "UserAdminController", description = "用户后台控制器")
public class UserAdminController extends AbstractController {

    private final UserService userService;

    @Operation(summary = "获取客户端列表")
    @ApiResponse(description = "获取客户端列表")
    @GetMapping(API_V1_ADMIN__USER__LIST)
    @OperationLog("获取客户端列表")
    public R<?> list(PageRequest pageRequest) {
        return R.success(userService.findByPage(pageRequest.getPageable()));
    }

}
