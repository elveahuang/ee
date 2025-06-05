package cc.elvea.platform.auth.web.controller;

import cc.elvea.platform.commons.annotations.OperationLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author elvea
 */
@Controller
@RequestMapping("/")
@Tag(name = "IndexController", description = "首页控制器")
public class IndexController {

    @Operation(summary = "首页")
    @ApiResponse(description = "首页")
    @GetMapping("/")
    @OperationLog("首页")
    public String index() {
        return "index";
    }

    @Operation(summary = "关于页")
    @ApiResponse(description = "关于页")
    @GetMapping("/about")
    @OperationLog("关于页")
    public String about() {
        return "about";
    }

}
