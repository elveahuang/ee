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
 * 用户-角色
 *
 * @author elvea
 */
@Data
@Builder
@TableName("sys_user_role")
public class UserRoleEntity implements IdEntity {
    /**
     * 主键
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 用户ID
     */
    private Long userId;
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
}
