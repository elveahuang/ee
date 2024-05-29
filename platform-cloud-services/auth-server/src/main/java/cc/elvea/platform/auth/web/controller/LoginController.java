package cc.elvea.platform.auth.web.controller;

import cc.elvea.platform.commons.annotations.OperationLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author elvea
 * @since 24.1.0
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
