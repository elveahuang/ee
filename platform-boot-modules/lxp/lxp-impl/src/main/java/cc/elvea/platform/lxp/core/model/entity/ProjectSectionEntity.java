package cc.elvea.platform.lxp.core.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lxp_project_section")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class ProjectSectionEntity extends BaseEntity {
    /**
     * 项目ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long projectId;
    /**
     * 编号
     */
    private String code;
    /**
     * 名称
     */
    private String title;
    /**
     * 备注
     */
    private String description;
    /**
     * 发布状态
     */
    private Integer status;
}
