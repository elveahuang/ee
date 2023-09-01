package cn.elvea.platform.auth.web.controller;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author elvea
 * @since 0.0.1
 */
@Controller
public class LoginController {

    @Operation(summary = "登录页")
    @ApiResponse(description = "登录页")
    @GetMapping("/login")
    @OperationLog("登录页")
    public String login() {
        return "login";
    }

}
