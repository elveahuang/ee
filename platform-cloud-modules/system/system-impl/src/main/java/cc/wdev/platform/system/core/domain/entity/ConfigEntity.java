package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.core.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@TableName("sys_config")
public class ConfigEntity implements IdEntity {
    /**
     * 主键
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 参数名
     */
    private String code;
    /**
     * 参数值
     */
    private String value;
    /**
     * 备注
     */
    private String description;
    /**
     * 文本
     */
    private String label;
    /**
     * 来源
     */
    private Integer source;
    /**
     * 启用状态
     */
    private Boolean active;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedBy;
    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;
    /**
     * 删除人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deletedBy;
}
