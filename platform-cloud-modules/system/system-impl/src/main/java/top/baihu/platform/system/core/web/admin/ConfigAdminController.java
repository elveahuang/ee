package top.baihu.platform.system.core.web.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.web.controller.AbstractController;

/**
 * 系统设置管理控制器
 *
 * @author elvea
 */
@Slf4j
@RestController
@Tag(name = "ConfigMgrController", description = "系统设置管理控制器")
public class ConfigAdminController extends AbstractController {
}
