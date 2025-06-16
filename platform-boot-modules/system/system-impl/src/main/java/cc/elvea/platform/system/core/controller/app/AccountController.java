package cc.elvea.platform.system.core.controller.app;

import cc.elvea.platform.commons.annotations.Anonymous;
import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.annotations.RateLimiter;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.utils.SecurityUtils;
import cc.elvea.platform.system.core.api.AccountApi;
import cc.elvea.platform.system.core.model.dto.AccountForgotPasswordDto;
import cc.elvea.platform.system.core.model.dto.AccountInfoDto;
import cc.elvea.platform.system.core.model.form.*;
import cc.elvea.platform.system.core.model.request.AccountCheckRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.commons.enums.RateLimitTypeEnum.IP;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "AccountController", description = "账号控制器")
public class AccountController {

    private final AccountApi accountApi;

    @Anonymous
    @OperationLog("检查用户名是否可用")
    @Operation(summary = "检查用户名是否可用")
    @ApiResponse(description = "检查用户名是否可用")
    @GetMapping(API_V1__ACCOUNT__CHECK_USERNAME)
    @RateLimiter(type = IP)
    public R<Boolean> checkUsername(AccountCheckRequest request) {
        return R.success(accountApi.check(request));
    }

    @Anonymous
    @OperationLog("检查邮箱是否可用")
    @Operation(summary = "检查邮箱是否可用")
    @ApiResponse(description = "检查邮箱是否可用")
    @GetMapping(API_V1__ACCOUNT__CHECK_EMAIL)
    @RateLimiter(type = IP)
    public R<Boolean> checkEmail(AccountCheckRequest request) {
        return R.success(accountApi.check(request));
    }

    @Anonymous
    @OperationLog("检查手机号码是否可用")
    @Operation(summary = "检查手机号码是否可用")
    @ApiResponse(description = "检查手机号码是否可用")
    @GetMapping(API_V1__ACCOUNT__CHECK_MOBILE)
    @RateLimiter(type = IP)
    public R<Boolean> checkMobile(AccountCheckRequest request) {
        return R.success(accountApi.check(request));
    }

    @Authenticated
    @Operation(summary = "获取当前用户信息")
    @ApiResponse(description = "获取当前用户信息")
    @GetMapping(API_V1__ACCOUNT__INFO)
    public R<AccountInfoDto> user() {
        String curUsername = SecurityUtils.getUsername();
        return R.success(this.accountApi.getUserInfo(curUsername));
    }

    @Anonymous
    @OperationLog("用户注册")
    @Operation(summary = "用户注册")
    @ApiResponse(description = "用户注册")
    @PostMapping(API_V1__ACCOUNT__REGISTER)
    public R<?> register(@Valid AccountRegisterForm form) {
        return accountApi.register(form);
    }

    @Anonymous
    @Operation(summary = "退出登录")
    @ApiResponse(description = "退出登录")
    @OperationLog("退出登录")
    @PostMapping(API_V1__ACCOUNT__LOGOUT)
    public R<?> logout() {
        return accountApi.logout();
    }

    @Anonymous
    @Operation(summary = "忘记密码")
    @ApiResponse(description = "忘记密码")
    @OperationLog("忘记密码")
    @PostMapping(API_V1__ACCOUNT__FORGOT_PASSWORD)
    public R<AccountForgotPasswordDto> forgotPassword(@Valid AccountForgotPasswordForm form) {
        return accountApi.forgotPassword(form);
    }

    @Anonymous
    @Operation(summary = "重置密码")
    @ApiResponse(description = "重置密码")
    @OperationLog("重置密码")
    @PostMapping(API_V1__ACCOUNT__RESET_PASSWORD)
    public R<?> resetPassword(@Valid AccountResetPasswordForm form) {
        return accountApi.resetPassword(form);
    }

    @Authenticated
    @Operation(summary = "修改密码")
    @ApiResponse(description = "修改密码")
    @OperationLog("编辑个人资料")
    @PostMapping(API_V1__ACCOUNT__CHANGE_PASSWORD)
    public R<?> changePassword(@Valid AccountChangePasswordForm form) {
        return accountApi.changePassword(form);
    }

    @Authenticated
    @Operation(summary = "编辑个人资料")
    @ApiResponse(description = "编辑个人资料")
    @OperationLog("编辑个人资料")
    @PostMapping(API_V1__ACCOUNT__UPDATE)
    public R<?> updateAccount(@Valid AccountBaseForm form) {
        return accountApi.updateAccount(form);
    }

}
