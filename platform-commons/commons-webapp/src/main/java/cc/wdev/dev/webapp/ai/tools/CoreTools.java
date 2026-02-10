package cc.wdev.dev.webapp.ai.tools;

import cc.wdev.dev.webapp.ai.vo.JobVo;
import cc.wdev.dev.webapp.es.domain.entity.CourseElasticEntity;
import cc.wdev.dev.webapp.es.service.CourseElasticService;
import cc.wdev.platform.commons.web.request.PageRequest;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author elvea
 */
@Component
public class CoreTools {

    private CourseElasticService courseElasticService;

    @Tool(name = "searchJob", description = """
        搜索职位
        """)
    public List<JobVo> searchJob(@ToolParam(description = "关键字") String keyword) {
        return List.of(JobVo.builder().name("仓管员").description("负责管理仓库库存和订单").build());
    }

    @Tool(name = "searchCourse", description = """
        搜索课程
        """)
    public List<CourseElasticEntity> searchCourse(@ToolParam(description = "关键字") String keyword) {
        PageRequest request = PageRequest.builder().page(1).size(10).build();
        request.setQ(keyword);
        Page<CourseElasticEntity> page = courseElasticService.search(request);
        if (page.isEmpty()) {
            return List.of();
        }
        return page.getContent().stream().toList();
    }

    @Autowired
    public void setCourseElasticService(CourseElasticService courseElasticService) {
        this.courseElasticService = courseElasticService;
    }

}
