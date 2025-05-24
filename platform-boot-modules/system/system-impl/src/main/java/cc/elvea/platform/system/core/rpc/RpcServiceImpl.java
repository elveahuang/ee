package cc.elvea.platform.system.core.rpc;

import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
public class RpcServiceImpl implements RpcService {

    /**
     * @see RpcService#sayHello(String)
     */
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
