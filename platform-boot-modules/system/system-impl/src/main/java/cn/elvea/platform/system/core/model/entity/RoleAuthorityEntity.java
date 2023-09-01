package cn.elvea.platform.system.core.model.entity;

import cn.elvea.platform.commons.core.data.jpa.domain.SimpleEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 0.0.1
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_role_authority")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class RoleAuthorityEntity extends SimpleEntity {
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
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    private Long createdBy;
}
