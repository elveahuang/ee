package cn.elvea.platform.system.core.web.admin;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.commons.constants.SystemMappingConstants;
import cn.elvea.platform.system.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
 */
@AllArgsConstructor
@RestController
@Tag(name = "UserAdminController", description = "用户后台控制器")
public class UserAdminController extends AbstractController {

    private final UserService userService;

    @PermitAll
    @OperationLog("后台管理-获取用户信息")
    @Operation(summary = "获取当前用户信息")
    @ApiResponse(description = "获取当前用户信息")
    @GetMapping(SystemMappingConstants.API_V1_ADMIN__USER__LIST)
    public R<?> user() {
        return R.success(this.userService.findAll());
    }

}
