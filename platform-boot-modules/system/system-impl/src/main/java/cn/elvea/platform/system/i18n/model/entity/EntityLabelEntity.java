package cn.elvea.platform.system.i18n.model.entity;

import cn.elvea.platform.commons.core.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "sys_entity_label")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class EntityLabelEntity extends BaseEntity {
    /**
     * 实体类名
     */
    private String className;
    /**
     * 实体属性名
     */
    private String propertyName;
    /**
     * 简体中文
     */
    private String zhLabel;
    /**
     * 繁体中文
     */
    private String zhTwLabel;
    /**
     * 英语
     */
    private String enLabel;
    /**
     * 法语
     */
    private String frLabel;
    /**
     * 日语
     */
    private String jaLabel;
}
