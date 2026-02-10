package cc.wdev.platform.system.core.controller.admin;

import cc.wdev.platform.commons.web.servlet.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签管理控制器
 *
 * @author elvea
 */
@RestController
@Tag(name = "tag-mgr", description = "标签管理控制器")
public class TagAdminController extends AbstractController {
}
