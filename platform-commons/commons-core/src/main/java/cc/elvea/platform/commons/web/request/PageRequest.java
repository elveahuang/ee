package cc.elvea.platform.commons.web.request;

import com.google.common.base.Strings;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageRequest extends Request {
    /**
     * 页码
     */
    @Schema(title = "页码", defaultValue = "1")
    private int page = 1;
    /**
     * 记录数
     */
    @Schema(title = "记录数", defaultValue = "10")
    private int size = 10;
    /**
     * 排序字段
     */
    @Schema(title = "排序字段", description = "id", example = "id")
    private String sort;
    /**
     * 排序方式
     */
    @Schema(title = "排序方式")
    private String order;
    /**
     * 搜索关键字
     */
    @Schema(title = "搜索关键字")
    private String q;

    public Pageable getPageable() {
        if (!Strings.isNullOrEmpty(sort)) {
            Sort.Direction direction = "desc".equalsIgnoreCase(this.getOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;
            return org.springframework.data.domain.PageRequest.of(this.page - 1, size, Sort.by(direction, sort));
        } else {
            return org.springframework.data.domain.PageRequest.of(this.page - 1, size);
        }
    }

}
