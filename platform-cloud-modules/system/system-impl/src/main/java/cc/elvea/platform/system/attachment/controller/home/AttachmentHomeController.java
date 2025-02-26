package cc.elvea.platform.system.attachment.controller.home;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.system.attachment.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件控制器
 *
 * @author elvea
 */
@RestController
@Tag(name = "AttachmentController", description = "附件控制器")
public class AttachmentHomeController {

    private AttachmentService attachmentService;

    /**
     * 上传文件
     */
    @Operation(summary = "附件上传")
    @ApiResponse(description = "附件上传")
    @RequestMapping("/attachment/upload")
    @Parameters({
            @Parameter(name = "file", description = "附件", required = true),
    })
    public R<?> uploadAttachmentFile(@RequestParam(value = "file") MultipartFile file) {
        this.attachmentService.uploadFile();
        return R.success();
    }

    @Autowired
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

}
