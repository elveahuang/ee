package cn.elvea.platform.system.link.web;

import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.commons.constants.SystemMappingConstants;
import cn.elvea.platform.system.link.model.dto.LinkDto;
import cn.elvea.platform.system.link.service.LinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
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
