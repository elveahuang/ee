package cn.elvea.platform.system.core.web.admin;

import cn.elvea.platform.commons.core.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@Tag(name = "AuthorityAdminController", description = "权限后台控制器")
public class AuthorityAdminController extends AbstractController {
}
