package cc.elvea.platform.system.tag.controller.home;

import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签控制器
 *
 * @author elvea
 * @since 24.1.0
 */
@RestController
@Tag(name = "tag", description = "标签控制器")
public class TagHomeController extends AbstractController {
}
