package cc.elvea.platform.commons.core.ai.tools;

import cc.elvea.platform.commons.constants.GlobalConstants;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.annotation.RegisterReflection;

@RegisterReflection(memberCategories = MemberCategory.INVOKE_DECLARED_METHODS)
public class CommonTools {

    @Tool(description = "问好")
    public String greeting(@ToolParam(description = "姓名") String name) {
        return "！你好呀" + name;
    }

    @Tool(description = "获取版本号")
    public String getVersion() {
        return GlobalConstants.VERSION;
    }

}
