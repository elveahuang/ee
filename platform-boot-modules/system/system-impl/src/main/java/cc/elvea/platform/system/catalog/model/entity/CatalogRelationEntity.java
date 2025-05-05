package cc.elvea.platform.system.catalog.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.SimpleEntity;
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
@Table(name = "sys_catalog_relation")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class CatalogRelationEntity extends SimpleEntity {
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
     * 关联类型
     */
    private String relationType;
    /**
     * 关联路径
     */
    private String relationPath;
    /**
     * 关联层级
     */
    private Integer relationIndex;
}
