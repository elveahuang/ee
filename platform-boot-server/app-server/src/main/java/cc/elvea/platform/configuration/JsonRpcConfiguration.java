package cc.elvea.platform.configuration;

import cc.elvea.platform.system.core.rpc.RpcService;
import com.xxl.tool.jsonrpc.JsonRpcServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class JsonRpcConfiguration {

    public JsonRpcConfiguration(JsonRpcServer jsonRpcServer, RpcService rpcService) {
        jsonRpcServer.register("rpcService", rpcService);
    }

}
