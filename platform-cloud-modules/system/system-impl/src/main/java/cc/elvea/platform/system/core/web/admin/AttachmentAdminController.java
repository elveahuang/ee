package cc.elvea.platform.system.core.web.admin;

import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.system.core.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@RestController
@RequestMapping("/api/admin")
@Tag(name = "AttachmentMgrController", description = "附件管理控制器")
public class AttachmentAdminController {

    private AttachmentService attachmentService;

    @Autowired
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    /**
     * 查询单个角色类型
     *
     * @return 查询结果
     */
    @Operation(summary = "用户注册")
    @ApiResponse(description = "用户注册")
    @PostMapping(value = "/attachment/list")
    public R<?> index() {
        return R.success();
    }

}
