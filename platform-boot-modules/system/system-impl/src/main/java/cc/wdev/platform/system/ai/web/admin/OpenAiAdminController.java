package cc.wdev.platform.system.ai.web.admin;

import cc.wdev.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "OpenAiAdminController", description = "OpenAI后台控制器")
public class OpenAiAdminController extends AbstractController {
}
