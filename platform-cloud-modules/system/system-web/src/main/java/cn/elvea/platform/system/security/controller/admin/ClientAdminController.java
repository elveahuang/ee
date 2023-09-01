package cn.elvea.platform.system.security.controller.admin;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.request.PageRequest;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.security.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__CLIENT__LIST;

/**
 * @author elvea
 * @since 0.0.1
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
