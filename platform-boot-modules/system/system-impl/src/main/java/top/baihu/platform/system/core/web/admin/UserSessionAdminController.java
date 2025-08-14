package top.baihu.platform.system.core.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.web.controller.AbstractController;
import top.baihu.platform.system.commons.constants.SystemMappingConstants;
import top.baihu.platform.system.core.service.UserSessionService;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
@Tag(name = "UserSessionAdminController", description = "用户会话后台控制器")
public class UserSessionAdminController extends AbstractController {

    private final UserSessionService userSessionService;

    @PermitAll
    @Operation(summary = "获取当前用户信息")
    @ApiResponse(description = "获取当前用户信息")
    @GetMapping(SystemMappingConstants.API_V1_ADMIN__USER_SESSION__LIST)
    public R<?> user() {
        return R.success(this.userSessionService.findAll());
    }

}
