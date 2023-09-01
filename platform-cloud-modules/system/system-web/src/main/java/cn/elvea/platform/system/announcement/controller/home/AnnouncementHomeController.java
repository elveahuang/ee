package cn.elvea.platform.system.announcement.controller.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/announcement")
@Tag(name = "AnnouncementController", description = "公告控制器")
public class AnnouncementHomeController {
}
