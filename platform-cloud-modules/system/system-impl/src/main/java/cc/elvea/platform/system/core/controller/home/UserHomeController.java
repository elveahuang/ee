package cc.elvea.platform.system.core.controller.home;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.core.model.dto.UserForgotPasswordDto;
import cc.elvea.platform.system.core.model.dto.UserRegisterDto;
import cc.elvea.platform.system.core.model.vo.UserInfoVo;
import cc.elvea.platform.system.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@Slf4j
@RestController
@Tag(name = "UserHomeController", description = "用户前台控制器")
public class UserHomeController extends AbstractController {

    private UserService userService;

    /**
     * 用户注册
     *
     * @return 用户登录信息
     */
    @Operation(summary = "用户注册")
    @ApiResponse(description = "用户注册")
    @PostMapping(API_V1__USER__REGISTER)
    @OperationLog("用户注册")
    public R<String> register(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        this.userService.register(userRegisterDto);
        return R.success();
    }

    /**
     * 用户忘记密码
     *
     * @return 用户忘记密码
     */
    @Operation(summary = "用户忘记密码")
    @ApiResponse(description = "用户忘记密码")
    @PostMapping(API_V1__USER__FORGOT_PASSWORD)
    @OperationLog("用户忘记密码")
    public R<?> forgotPassword(UserForgotPasswordDto forgotPasswordDto) {
        this.userService.forgotPassword(forgotPasswordDto);
        return R.success();
    }

    /**
     * 获取用户登录信息
     *
     * @return 用户登录信息
     */
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "获取用户登录信息")
    @ApiResponse(description = "获取用户登录信息")
    @GetMapping(API_V1__USER__INFO)
    @OperationLog("获取用户登录信息")
    public R<UserInfoVo> version() {
        return R.success();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
