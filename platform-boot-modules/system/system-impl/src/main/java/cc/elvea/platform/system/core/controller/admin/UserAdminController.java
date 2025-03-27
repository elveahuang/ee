package cc.elvea.platform.system.core.controller.admin;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import cc.elvea.platform.system.core.model.form.UserForm;
import cc.elvea.platform.system.core.model.request.UserDeleteRequest;
import cc.elvea.platform.system.core.model.request.UserSearchRequest;
import cc.elvea.platform.system.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
@Tag(name = "UserAdminController", description = "用户后台控制器")
public class UserAdminController extends AbstractController {

    private final UserService userService;

    @Operation(summary = "获取用户列表")
    @ApiResponse(description = "获取用户列表")
    @PostMapping(API_V1_ADMIN__USER__LIST)
    @OperationLog("获取用户列表")
    public R<Page<UserEntity>> list(UserSearchRequest searchRequest) {
        return R.success(this.userService.findByPage(searchRequest.getPageable()));
    }

    @Operation(summary = "获取用户详情")
    @ApiResponse(description = "获取用户详情")
    @PostMapping(API_V1_ADMIN__USER__DETAILS)
    @OperationLog("获取用户详情")
    public R<UserEntity> details(@RequestParam Long id) {
        return R.success(this.userService.findById(id));
    }

    @Operation(summary = "准备用户资讯")
    @ApiResponse(description = "准备用户资讯")
    @PostMapping(API_V1_ADMIN__USER__PREPARE)
    @OperationLog("准备用户资讯")
    public R<UserEntity> prepare(@RequestParam Long id) {
        return R.success(this.userService.findById(id));
    }

    @Operation(summary = "保存用户资讯")
    @ApiResponse(description = "保存用户资讯")
    @PostMapping(API_V1_ADMIN__USER__SAVE)
    @OperationLog("保存用户资讯")
    public R<?> save(@Valid UserForm form) {
        this.userService.saveUser(form);
        return R.success();
    }

    @Operation(summary = "删除用户资讯")
    @ApiResponse(description = "删除用户资讯")
    @PostMapping(API_V1_ADMIN__USER__DELETE)
    @OperationLog("删除用户资讯")
    public R<?> delete(UserDeleteRequest request) {
        if (request != null && request.getIds() != null && request.getIds().length > 0) {
            this.userService.deleteBatchById(Arrays.asList(request.getIds()));
        }
        return R.success();
    }

}
