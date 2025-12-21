package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.jpa.domain.SimpleEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user_role")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class UserRoleEntity extends SimpleEntity {
    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
}
