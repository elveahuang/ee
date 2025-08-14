package top.baihu.platform.system.core.web.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.web.controller.AbstractController;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "SystemAttachmentController", description = "附件管理控制器")
public class AttachmentAdminController extends AbstractController {
}
