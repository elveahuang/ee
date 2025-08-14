package top.baihu.platform.system.ai.web.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.web.controller.AbstractController;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "OpenAiAdminController", description = "OpenAI后台控制器")
public class OpenAiAdminController extends AbstractController {
}
