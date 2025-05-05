package cc.elvea.platform.commons.data.elasticsearch.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Getter
@Setter
public abstract class BaseEntity extends AbstractEntity {
    /**
     * 启用状态
     */
    private Boolean active;
    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @CreatedBy
    private Long createdBy;
    /**
     * 最后修改时间
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    /**
     * 最后修改人
     */
    @LastModifiedBy
    private Long lastModifiedBy;
    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;
    /**
     * 删除人
     */
    private Long deletedBy;
}
