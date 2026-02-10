package cc.wdev.platform.system.core.controller.admin;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import cc.wdev.platform.system.commons.constants.SystemMappingConstants;
import cc.wdev.platform.system.core.service.LoginSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
@Tag(name = "LoginSessionAdminController", description = "会话后台控制器")
public class LoginSessionAdminController extends AbstractController {

    private final LoginSessionService userSessionService;

    @PermitAll
    @Operation(summary = "获取当前用户信息")
    @ApiResponse(description = "获取当前用户信息")
    @GetMapping(SystemMappingConstants.API_V1_ADMIN__USER_SESSION__LIST)
    public R<?> user() {
        return R.success(this.userSessionService.findAll());
    }

}
