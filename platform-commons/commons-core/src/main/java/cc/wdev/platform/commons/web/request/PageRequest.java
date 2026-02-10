package cc.wdev.platform.commons.web.request;

import cc.wdev.platform.commons.data.jdbc.utils.JdbcUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PageRequest extends Request {
    /**
     * 页码
     */
    @Builder.Default
    @Schema(title = "页码", defaultValue = "1", description = "当前页码")
    private int page = 1;
    /**
     * 记录数
     */
    @Builder.Default
    @Schema(title = "记录数", defaultValue = "10", description = "每页记录数")
    private int size = 10;
    /**
     * 排序字段
     */
    @Schema(title = "排序字段", example = "id", defaultValue = "id", description = "排序字段")
    private String sort;
    /**
     * 排序方式
     */
    @Schema(title = "排序方式", description = "排序方式", example = "asc")
    private String order;
    /**
     * 搜索关键字
     */
    @Schema(title = "搜索关键字", description = "搜索关键字")
    private String q;

    /**
     * 获取分页对象
     */
    @Schema(title = "分页对象", description = "分页对象")
    public Pageable getPageable() {
        if (StringUtils.isNotEmpty(sort)) {
            Sort.Direction direction = Sort.Direction.fromOptionalString(this.getOrder()).orElse(Sort.Direction.ASC);
            return org.springframework.data.domain.PageRequest.of(this.page - 1, size, Sort.by(direction, sort));
        } else {
            return org.springframework.data.domain.PageRequest.of(this.page - 1, size);
        }
    }

    /**
     * 转换成模糊搜索
     */
    @Schema(title = "模糊搜索", description = "模糊搜索字符")
    public String getQLike() {
        return JdbcUtils.generateLike(q);
    }

}
