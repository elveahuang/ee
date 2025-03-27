package cc.elvea.platform.system.core.controller.admin;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.core.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "RoleMgrController", description = "角色管理控制器")
public class RoleAdminController extends AbstractController {

    private final RoleService roleService;

    /**
     * 查询单个角色类型
     *
     * @return 查询结果
     */
    @Operation(summary = "用户注册")
    @ApiResponse(description = "用户注册")
    @PostMapping(value = "/role/find-by-code")
    public R<?> getById(@RequestBody String code) {
        return R.success();
    }

}
