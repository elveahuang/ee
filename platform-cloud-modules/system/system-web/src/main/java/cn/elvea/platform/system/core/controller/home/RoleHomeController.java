package cn.elvea.platform.system.core.controller.home;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.core.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制器
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/api/")
@Tag(name = "RoleController", description = "角色控制器")
public class RoleHomeController extends AbstractController {

    private final RoleService roleService;

    public RoleHomeController(RoleService roleService) {
        this.roleService = roleService;
    }

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
