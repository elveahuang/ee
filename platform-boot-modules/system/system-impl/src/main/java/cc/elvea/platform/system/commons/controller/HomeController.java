package cc.elvea.platform.system.commons.controller;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.core.Context;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.commons.utils.platform.Platform;
import cc.elvea.platform.commons.utils.platform.PlatformHelper;
import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @author elvea
 */
@AllArgsConstructor
@Controller
@RequestMapping("/")
@Tag(name = "HomeController", description = "首页控制器")
public class HomeController extends AbstractController {

    private final Context context;

    @Operation(summary = "首页")
    @ApiResponse(description = "首页")
    @GetMapping("/")
    @OperationLog("首页")
    public void index(HttpServletResponse response) throws IOException {
        String page;
        Platform platform = PlatformHelper.fromServletRequest();
        if (platform.isMobile()) {
            page = StringUtils.isNotEmpty(this.context.getHome().getMobile()) ? this.context.getHome().getMobile() : "";
        } else {
            page = StringUtils.isNotEmpty(this.context.getHome().getWebapp()) ? this.context.getHome().getWebapp() : "";
        }
        if (StringUtils.isEmpty(page)) {
            page = StringUtils.isNotEmpty(this.context.getHome().getMain()) ? this.context.getHome().getMain() : "home";
        }
        response.sendRedirect(page);
    }

    @Operation(summary = "主页")
    @ApiResponse(description = "主页")
    @GetMapping("/home")
    @OperationLog("主页")
    public String home() {
        return "index";
    }

}
