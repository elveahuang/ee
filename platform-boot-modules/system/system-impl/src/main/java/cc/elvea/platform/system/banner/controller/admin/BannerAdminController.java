package cc.elvea.platform.system.banner.controller.admin;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.banner.model.entity.BannerEntity;
import cc.elvea.platform.system.banner.model.form.BannerForm;
import cc.elvea.platform.system.banner.model.request.BannerDeleteRequest;
import cc.elvea.platform.system.banner.model.request.BannerSearchRequest;
import cc.elvea.platform.system.banner.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "BannerAdminController", description = "宣传栏管理端控制器")
public class BannerAdminController extends AbstractController {

    private final BannerService bannerService;

    @PreAuthorize("hasAnyAuthority('site:poster')")
    @Operation(summary = "获取宣传栏列表")
    @ApiResponse(description = "获取宣传栏列表")
    @PostMapping(API_V1_ADMIN__BANNER__LIST)
    @OperationLog("获取宣传栏列表")
    public R<?> list(BannerSearchRequest request) {
        BannerEntity example = BannerEntity.builder().build();
        example.setActive(Boolean.TRUE);
        return R.success(bannerService.findByPage(request.getPageable(), example));
    }

    @PreAuthorize("hasAnyAuthority('site:poster:view')")
    @Operation(summary = "获取宣传栏详情")
    @ApiResponse(description = "获取宣传栏详情")
    @PostMapping(API_V1_ADMIN__BANNER__DETAILS)
    @OperationLog("获取宣传栏详情")
    public R<BannerEntity> details(@RequestParam("id") Long id) {
        return R.success(bannerService.findById(id, bannerService::getExtra));
    }

    @PreAuthorize("hasAnyAuthority('site:poster:add', 'coin:warn:edit')")
    @Operation(summary = "保存宣传栏")
    @ApiResponse(description = "保存宣传栏")
    @PostMapping(API_V1_ADMIN__BANNER__SAVE)
    @OperationLog("保存宣传栏")
    public R<?> save(@RequestBody BannerForm bannerForm) {
        bannerService.saveBanner(bannerForm);
        return R.success();
    }

    @PreAuthorize("hasAnyAuthority('site:poster:delete')")
    @Operation(summary = "删除宣传栏")
    @ApiResponse(description = "删除宣传栏")
    @PostMapping(API_V1_ADMIN__BANNER__DELETE)
    @OperationLog("删除宣传栏")
    public R<?> delete(@Valid BannerDeleteRequest request) {
        if (request != null && request.getIds() != null) {
            for (Long id : request.getIds()) {
                bannerService.deleteById(id);
            }
        }
        return R.success();
    }

}
