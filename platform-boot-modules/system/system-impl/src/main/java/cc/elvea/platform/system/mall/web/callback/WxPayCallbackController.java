package cc.elvea.platform.system.mall.web.callback;

import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "WxPayCallbackController", description = "微信支付回调接口")
public class WxPayCallbackController extends AbstractController {
}
