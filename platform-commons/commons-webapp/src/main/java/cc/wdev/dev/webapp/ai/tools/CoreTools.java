package cc.wdev.dev.webapp.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.List;

/**
 * @author elvea
 */
public class CoreTools {

    @Tool(name = "checkUsername", description = """
        检测用户名
        """)
    public boolean checkUsername(@ToolParam(description = "用户名") String username) {
        return true;
    }

    @Tool(name = "searchActivity", description = """
        搜索课程
        """)
    public String searchActivity(@ToolParam(description = "关键词") String keyword) {
        return "《山海经》、《红楼梦》、《水浒传》、《西游记》、《金瓶梅》";
    }

    @Tool(name = "searchInstructor", description = """
        搜索讲师
        """)
    public List<String> searchInstructor(@ToolParam(description = "关键词") String keyword) {
        return List.of("张三", "李四");
    }

    @Tool(name = "getRecommendActivity", description = """
        获取推荐课程
        仅当用户询问“我有能学课程”、“适合我的有哪些课程”、“我的课表”等相关的问题时调用。
        """)
    public String getRecommendActivity(@ToolParam(description = "用户名") String username) {
        return "《山海经》、《红楼梦》、《水浒传》、《西游记》、《金瓶梅》";
    }

    @Tool(name = "getMyActivity", description = """
        获取我的课程
        仅当用户询问“我有哪些课程”、“我已经选了什么课”、“我的课表”等与**已报名/注册/已选课程**相关的问题时调用。
        不要用于推荐、搜索或他人课程。
        """)
    public List<String> getMyActivity(@ToolParam(description = "用户名") String username) {
        return List.of("红楼梦", "水浒传");
    }

}
