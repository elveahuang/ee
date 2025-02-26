package cc.elvea.platform.system.catalog.model.entity;

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
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_catalog")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class CatalogEntity extends BaseEntity {
    /**
     * 类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;

    private String code;
    private String title;
}
