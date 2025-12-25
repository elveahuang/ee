package cc.wdev.dev.webapp.ai.tools;

import cc.wdev.dev.webapp.ai.vo.JobVo;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.List;

/**
 * @author elvea
 */
public class CoreTools {

    @Tool(name = "searchJob", description = """
        搜索职位
        """)
    public List<JobVo> searchJob(@ToolParam(description = "关键字") String keyword) {
        return List.of(JobVo.builder().name("仓管员").description("负责管理仓库库存和订单").build());
    }

}
