package cc.elvea.platform.system.core.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_role")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity extends BaseEntity {
    /**
     * 编号
     */
    private String code;
    /**
     * 文本
     */
    private String label;
    /**
     * 名称
     */
    private String title;
    /**
     * 备注
     */
    private String description;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 来源
     */
    private Integer source;
}
