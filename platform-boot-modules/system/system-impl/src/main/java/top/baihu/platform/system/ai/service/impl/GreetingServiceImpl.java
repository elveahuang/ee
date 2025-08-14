package top.baihu.platform.system.ai.service.impl;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import top.baihu.platform.system.ai.service.GreetingService;

/**
 * @author elvea
 */
@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    @Tool(description = "A simple greeting tool")
    public String greeting(String name) {
        return "Hello, " + name + "!";
    }

}
