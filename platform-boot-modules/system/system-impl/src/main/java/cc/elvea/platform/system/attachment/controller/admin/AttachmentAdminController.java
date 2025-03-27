package cc.elvea.platform.system.attachment.controller.admin;

import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "SystemAttachmentController", description = "附件管理控制器")
public class AttachmentAdminController extends AbstractController {
}
