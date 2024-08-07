package cc.elvea.platform.system.commons.web;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.utils.DateTimeUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.commons.utils.platform.Platform;
import cc.elvea.platform.commons.utils.platform.PlatformHelper;
import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 24.1.0
 */
@Controller
@RequestMapping("/")
@Tag(name = "HomeController", description = "首页控制器")
public class HomeController extends AbstractController {

    @Operation(summary = "首页")
    @ApiResponse(description = "首页")
    @GetMapping("/")
    @OperationLog("首页")
    public void index(@RequestParam("i") String i, HttpServletResponse response) throws IOException {
        String page = "/home";
        if ("1".equalsIgnoreCase(i)) {
            response.addCookie(new Cookie("a", DateTimeUtils.format(LocalDateTime.now(), DateTimeConstants.Pattern.FULL_TIME)));
            page = "/about";
        } else {
            if (this.context.isProductionMode()) {
                Platform platform = PlatformHelper.fromServletRequest();
                if (platform.isMobile()) {
                    page = StringUtils.isNotEmpty(this.context.getHome().getMobile()) ? this.context.getHome().getMobile() : "";
                } else {
                    page = StringUtils.isNotEmpty(this.context.getHome().getWebapp()) ? this.context.getHome().getWebapp() : "";
                }
            }
            if (StringUtils.isEmpty(page)) {
                page = StringUtils.isNotEmpty(this.context.getHome().getMain()) ? this.context.getHome().getMain() : "about";
            }
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

    @Operation(summary = "关于页")
    @ApiResponse(description = "关于页")
    @GetMapping("/about")
    @OperationLog("关于页")
    public String about() {
        return "about";
    }

}
