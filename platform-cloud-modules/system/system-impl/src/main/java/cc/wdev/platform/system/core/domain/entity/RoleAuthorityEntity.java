package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.core.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@Builder
@TableName("sys_role_authority")
public class RoleAuthorityEntity implements IdEntity {
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
     * 权限ID
     */
    private Long authorityId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createdBy;
}
