package cn.elvea.platform.system.core.web;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.utils.SecurityUtils;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.commons.constants.SystemMappingConstants;
import cn.elvea.platform.system.core.api.UserApi;
import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.form.UserRegisterForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
@Tag(name = "UserController", description = "用户控制器")
public class UserController {

    private final UserApi userApi;

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "获取当前用户信息")
    @ApiResponse(description = "获取当前用户信息")
    @GetMapping(SystemMappingConstants.API_V1__USER__INFO)
    public R<UserInfoDto> user() {
        String curUsername = SecurityUtils.getUserName();
        return R.success(this.userApi.getUserInfo(curUsername));
    }

    @PermitAll
    @OperationLog("用户注册")
    @Operation(summary = "用户注册")
    @ApiResponse(description = "用户注册")
    @PostMapping(SystemMappingConstants.API_V1__USER__REGISTER)
    public R<?> register(@Valid UserRegisterForm form) {
        return R.success();
    }

    @PermitAll
    @Operation(summary = "忘记密码")
    @ApiResponse(description = "忘记密码")
    @GetMapping(SystemMappingConstants.API_V1__USER__FORGOT_PASSWORD)
    public R<?> password() {
        return R.success();
    }

}
