package cn.elvea.platform.system.core.model.entity;

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
@Table(name = "sys_config")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class ConfigEntity extends BaseEntity {
    /**
     * 分组
     */
    private String group;
    /**
     * 标题
     */
    private String title;
    /**
     * 多语言文本
     */
    private String label;
    /**
     * 参数名
     */
    private String key;
    /**
     * 参数值
     */
    private String value;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 备注
     */
    private String description;
    /**
     * 帮助信息
     */
    private String help;
    /**
     * 数据来源
     */
    private Long source;
}
