package cc.elvea.platform.commons.core.ai.tools;

import cc.elvea.platform.commons.constants.GlobalConstants;
import org.springframework.ai.tool.annotation.Tool;

public class CommonTools {

    @Tool(description = "问好")
    public String greeting(String name) {
        return "你好" + name;
    }

    @Tool(description = "获取版本号")
    public String getVersion() {
        return GlobalConstants.VERSION;
    }

}
