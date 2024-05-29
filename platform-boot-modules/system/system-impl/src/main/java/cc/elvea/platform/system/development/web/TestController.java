package cc.elvea.platform.system.development.web;

import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.message.broadcast.manager.BroadcastManager;
import cc.elvea.platform.commons.web.R;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.development.broadcast.TestBroadcastMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__TEST__MESSAGE;

/**
 * @author elvea
 * @since 24.1.0
 */
@RestController
@AllArgsConstructor
@Tag(name = "TestController", description = "测试验证专用控制器")
public class TestController extends AbstractController {

    private BroadcastManager broadcastManager;

    @PermitAll
    @OperationLog("发送广播消息")
    @Operation(summary = "发送广播消息")
    @ApiResponse(description = "发送广播消息")
    @ResponseBody
    @PostMapping(API_V1__TEST__MESSAGE)
    public R<?> send(@RequestParam("message") String message) throws Exception {
        this.broadcastManager.getRabbitSender().sendMessage(new TestBroadcastMessage(message));
        return R.success(message);
    }

}
