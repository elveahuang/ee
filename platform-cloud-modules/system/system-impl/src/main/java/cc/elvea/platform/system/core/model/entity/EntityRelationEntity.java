package cc.elvea.platform.system.core.model.entity;

import cc.elvea.platform.commons.data.core.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@Builder
@TableName("sys_entity_relation")
public class EntityRelationEntity implements IdEntity {
    /**
     * 主键
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
    /**
     * 祖先ID
     */
    private Long ancestorId;
    /**
     * 实体ID
     */
    private Long entityId;
    /**
     * 关联类型
     */
    private String relationType;
    /**
     * 是否直接上级
     */
    private Boolean parentInd;
    /**
     * 层级路径
     */
    private String path_;
    /**
     * 层级序号
     */
    private Integer index_;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;
}
