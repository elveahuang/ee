package cn.elvea.platform.commons.core.web.request;

import com.google.common.base.Strings;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageRequest extends Request {

    /**
     * 当前页
     */
    @Schema(title = "页码", defaultValue = "1")
    private int page = 1;

    /**
     * 每页显示多少条记录
     */
    @Schema(title = "记录数", defaultValue = "10")
    private int size = 10;

    /**
     * 排序
     */
    @Schema(title = "排序字段", description = "id", example = "id")
    private String sort;

    /**
     * 排序规则
     */
    @Schema(title = "记录数", defaultValue = "")
    private String order;

    /**
     * 搜索关键字
     */
    @Schema(title = "搜索关键字")
    private String q;

    /**
     * 获取Spring Data的Pageable对象
     */
    public Pageable getPageable() {
        if (!Strings.isNullOrEmpty(sort)) {
            Sort.Direction direction = "desc".equalsIgnoreCase(this.getOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;
            return org.springframework.data.domain.PageRequest.of(this.page - 1, size, Sort.by(direction, sort));
        } else {
            return org.springframework.data.domain.PageRequest.of(this.page - 1, size);
        }
    }

}
