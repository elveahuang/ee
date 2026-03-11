package cc.wdev.platform.system.commons.controller;

import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import cc.wdev.platform.system.commons.constants.SystemMappingConstants;
import cc.wdev.platform.system.core.domain.dto.LinkDto;
import cc.wdev.platform.system.core.service.LinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
@Tag(name = "LinkController", description = "短链接控制器")
public class LinkController extends AbstractController {

    private final LinkService linkService;

    @Operation(summary = "生成链接")
    @ApiResponse(description = "生成链接")
    @GetMapping(SystemMappingConstants.API_V1__LINK__GENERATE)
    public LinkDto generate(HttpServletRequest request) {
        return this.linkService.generate(request);
    }

    @Operation(summary = "处理链接")
    @ApiResponse(description = "处理链接")
    @GetMapping(SystemMappingConstants.API_V1__LINK__GO)
    public String to(HttpServletRequest request) {
        return redirect(this.linkService.process(request));
    }

}
