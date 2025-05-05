package cc.elvea.platform.system.i18n.model.entity;

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
@Table(name = "sys_entity_label")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class EntityLabelEntity extends BaseEntity {
    /**
     * 语言ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long langId;
    /**
     * 语言编号
     */
    private String langCode;
    /**
     * 实体ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long entityId;
    /**
     * 实体类名
     */
    private String entityClass;
    /**
     * 实体属性名
     */
    private String entityProperty;
    /**
     * 简体中文
     */
    private String zhCnLabel;
    /**
     * 繁体中文
     */
    private String zhTwLabel;
    /**
     * 英语
     */
    private String enLabel;
    /**
     * 日语
     */
    private String jaLabel;
    /**
     * 韩语
     */
    private String krLabel;
    /**
     * 法语
     */
    private String frLabel;
}
