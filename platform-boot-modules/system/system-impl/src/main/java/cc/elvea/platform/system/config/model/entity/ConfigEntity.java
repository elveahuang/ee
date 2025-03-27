package cc.elvea.platform.system.config.model.entity;

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
@Table(name = "sys_config")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class ConfigEntity extends BaseEntity {
    /**
     * 标题1
     */
    private String title;
    /**
     * 多语言文本
     */
    private String label;
    /**
     * 分组
     */
    private String configGroup;
    /**
     * 参数名
     */
    private String configKey;
    /**
     * 参数值
     */
    private String configValue;
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
