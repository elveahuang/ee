package top.baihu.platform.auth.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.baihu.platform.commons.annotations.OperationLog;

/**
 * @author elvea
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
