package cc.elvea.platform.system.tag.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.SimpleEntity;
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
@Table(name = "sys_tag_relation")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class TagRelationEntity extends SimpleEntity {
    /**
     * 类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * 关联项ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long itemId;
    /**
     * 目标实体类型
     */
    private String targetType;
    /**
     * 目标实体ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long targetId;
}
