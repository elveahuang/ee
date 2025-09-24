package cc.wdev.platform.system.development.web;

import cc.wdev.platform.commons.annotations.Anonymous;
import cc.wdev.platform.commons.annotations.DateTimeFormat;
import cc.wdev.platform.commons.annotations.JsonFormat;
import cc.wdev.platform.commons.annotations.OperationLog;
import cc.wdev.platform.commons.constants.DateTimeConstants;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.message.broadcast.manager.BroadcastManager;
import cc.wdev.platform.commons.web.controller.AbstractController;
import cc.wdev.platform.system.development.broadcast.TestBroadcastMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1__TEST__DATE;
import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1__TEST__MESSAGE;

/**
 * @author elvea
 */
@RestController
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

    @Anonymous
    @OperationLog("测试时区")
    @Operation(summary = "测试时区")
    @ApiResponse(description = "测试时区")
    @ResponseBody
    @PostMapping(API_V1__TEST__DATE)
    public R<DateVo> date() throws Exception {
        return R.success(DateVo.builder().build());
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DateVo implements Serializable {

        @Builder.Default
        @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
        @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
        Date date = new Date();

        @Builder.Default
        @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
        @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
        LocalDateTime localDateTime = LocalDateTime.now();
    }

    @Autowired(required = false)
    public void setBroadcastManager(BroadcastManager broadcastManager) {
        this.broadcastManager = broadcastManager;
    }

}

