package cc.elvea.platform.system.config.web.admin;

import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 24.1.0
 */
@RestController
@Tag(name = "ConfigAdminController", description = "系统设置-后台控制器")
public class ConfigAdminController extends AbstractController {
}
