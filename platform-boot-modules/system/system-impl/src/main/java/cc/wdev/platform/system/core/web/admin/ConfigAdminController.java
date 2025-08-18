package cc.wdev.platform.system.core.web.admin;

import cc.wdev.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@RestController
@Tag(name = "ConfigAdminController", description = "系统设置-后台控制器")
public class ConfigAdminController extends AbstractController {
}
