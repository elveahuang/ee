package cc.elvea.platform.commons.extensions.jsonrpc.web;

import com.xxl.tool.jsonrpc.JsonRpcServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class JsonRpcController {

    private final JsonRpcServer jsonRpcServer;

    @RequestMapping("/jsonrpc")
    @ResponseBody
    public String jsonrpc(@RequestBody(required = false) String requestBody) {
        return jsonRpcServer.invoke(requestBody);
    }

}
