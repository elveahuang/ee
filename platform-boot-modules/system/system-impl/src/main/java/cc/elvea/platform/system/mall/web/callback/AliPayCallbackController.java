package cc.elvea.platform.system.mall.web.callback;

import cc.elvea.platform.commons.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 24.1.0
 */
@RestController
@AllArgsConstructor
@Tag(name = "AliPayCallbackController", description = "支付宝支付回调接口")
public class AliPayCallbackController extends AbstractController {
}
