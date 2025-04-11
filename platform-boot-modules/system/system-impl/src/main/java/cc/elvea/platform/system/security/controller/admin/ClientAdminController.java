package cc.elvea.platform.system.security.controller.admin;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.commons.web.request.PageRequest;
import cc.elvea.platform.system.security.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__CLIENT__LIST;

/**
 * @author elvea
 */
@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "ClientAdminController", description = "客户端管理控制器")
public class ClientAdminController extends AbstractController {

    private final ClientService clientService;

    @Operation(summary = "获取客户端列表")
    @ApiResponse(description = "获取客户端列表")
    @GetMapping(API_V1_ADMIN__CLIENT__LIST)
    @OperationLog("获取客户端列表")
    public R<?> list(PageRequest pageRequest) {
        return R.success(clientService.findByPage(pageRequest.getPageable()));
    }

}
