package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.jpa.domain.BaseEntity;
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
@Table(name = "sys_dict_item")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class DictItemEntity extends BaseEntity {
    /**
     * 类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * 编号
     */
    private String code;
    /**
     * 文本
     */
    private String title;
    /**
     * 序号
     */
    private Integer idx;
    /**
     * 来源
     */
    private Integer source;
}
