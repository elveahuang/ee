package top.baihu.platform.system.security.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.annotations.OperationLog;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.web.controller.AbstractController;
import top.baihu.platform.commons.web.request.PageRequest;
import top.baihu.platform.system.security.service.AuthorizationConsentService;

import static top.baihu.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__AUTHORIZATION_CONSENT__LIST;

/**
 * @author elvea
 */
@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "AuthorizationConsentAdminController", description = "客户端管理控制器")
public class AuthorizationConsentAdminController extends AbstractController {

    private final AuthorizationConsentService authorizationConsentService;

    @Operation(summary = "获取客户端列表")
    @ApiResponse(description = "获取客户端列表")
    @GetMapping(API_V1_ADMIN__AUTHORIZATION_CONSENT__LIST)
    @OperationLog("获取客户端列表")
    public R<?> list(PageRequest pageRequest) {
        return R.success(authorizationConsentService.findByPage(pageRequest.getPageable()));
    }

}
