package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.jpa.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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
     * 标题
     */
    @Schema(description = "标题")
    private String title;
    /**
     * 多语言文本
     */
    @Schema(description = "多语言文本")
    private String label;
    /**
     * 分组
     */
    @Schema(description = "分组")
    private String configGroupType;
    /**
     * 内容类型
     */
    @Schema(description = "内容类型")
    private String configContentType;
    /**
     * 参数名
     */
    @Schema(description = "参数名")
    private String configKey;
    /**
     * 参数值
     */
    @Schema(description = "参数值")
    private String configValue;
    /**
     * 默认值
     */
    @Schema(description = "默认值")
    private String defaultValue;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String description;
    /**
     * 帮助信息
     */
    @Schema(description = "帮助信息")
    private String help;
    /**
     * 数据来源
     */
    @Schema(description = "数据来源")
    private Long source;
}
