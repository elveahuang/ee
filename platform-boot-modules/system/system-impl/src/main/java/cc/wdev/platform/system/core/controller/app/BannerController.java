package cc.wdev.platform.system.core.controller.app;

import cc.wdev.platform.commons.annotations.Anonymous;
import cc.wdev.platform.commons.annotations.OperationLog;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import cc.wdev.platform.system.core.domain.entity.BannerEntity;
import cc.wdev.platform.system.core.domain.request.BannerSearchRequest;
import cc.wdev.platform.system.core.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1__BANNER__LIST;

/**
 * @author elvea
 */
@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "BannerController", description = "宣传栏控制器")
public class BannerController extends AbstractController {

    private final BannerService bannerService;

    @Anonymous
    @Operation(summary = "获取宣传栏列表")
    @ApiResponse(description = "获取宣传栏列表")
    @PostMapping(API_V1__BANNER__LIST)
    @OperationLog("获取宣传栏列表")
    public R<Page<BannerEntity>> list(BannerSearchRequest request) {
        return R.success(bannerService.findBannerForUser(request));
    }

}
