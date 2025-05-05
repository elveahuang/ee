package cc.elvea.platform.system.tag.controller.admin;

import cc.elvea.platform.commons.web.controller.AbstractController;
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
