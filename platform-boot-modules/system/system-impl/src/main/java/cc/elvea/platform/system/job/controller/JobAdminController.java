package cc.elvea.platform.system.job.controller;

import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
@Tag(name = "JobAdminController", description = "定时任务后台控制器")
public class JobAdminController extends AbstractController {
}
