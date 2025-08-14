package top.baihu.platform.system.core.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.web.controller.AbstractController;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
@Tag(name = "JobAdminController", description = "定时任务后台控制器")
public class JobAdminController extends AbstractController {
}
