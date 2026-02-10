package cc.wdev.platform.commons.core.ai.tools;

import cc.wdev.platform.commons.constants.GlobalConstants;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.annotation.RegisterReflection;

/**
 * @author elvea
 */
@RegisterReflection(memberCategories = MemberCategory.INVOKE_DECLARED_METHODS)
public class CommonTools {

    @Tool(description = "获取版本号", name = "getVersion")
    public String getVersion() {
        return GlobalConstants.VERSION;
    }

}
