package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.core.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_catalog_relation")
public class CatalogRelationEntity implements IdEntity {
    /**
     * 主键
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 关联类型
     */
    private String relationType;
    /**
     * 祖先ID
     */
    private Long ancestorId;
    /**
     * 实体ID
     */
    private Long entityId;
    /**
     * 是否直接上级
     */
    private Boolean parentInd;
    /**
     * 层级序号
     */
    private Integer relationIndex;
    /**
     * 层级路径
     */
    private String fullPath;
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
}
