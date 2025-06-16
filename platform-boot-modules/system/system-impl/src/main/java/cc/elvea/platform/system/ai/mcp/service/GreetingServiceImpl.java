package cc.elvea.platform.system.ai.mcp.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

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
