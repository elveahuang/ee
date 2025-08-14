package top.baihu.platform.system.mall.web.callback;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.web.controller.AbstractController;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "WxPayCallbackController", description = "微信支付回调接口")
public class WxPayCallbackController extends AbstractController {
}
