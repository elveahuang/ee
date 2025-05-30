package cc.elvea.platform.system.banner.model.entity;

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
@Table(name = "sys_banner")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class BannerEntity extends BaseEntity {
    /**
     * 标题
     */
    private String title;
    /**
     * 序号
     */
    private Integer idx;
    /**
     * 详情
     */
    private String details;
    /**
     * 备注
     */
    private String description;
}
