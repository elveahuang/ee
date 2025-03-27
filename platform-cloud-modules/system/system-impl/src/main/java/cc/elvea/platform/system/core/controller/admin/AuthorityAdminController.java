package cc.elvea.platform.system.core.controller.admin;

import cc.elvea.platform.commons.base.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限管理控制器
 *
 * @author elvea
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "AuthorityMgrController", description = "权限管理控制器")
public class AuthorityAdminController {

    @Operation(summary = "用户注册")
    @ApiResponse(description = "用户注册")
    @PostMapping(value = "/authority/list")
    public R<?> index() {
        return R.success();
    }

}
