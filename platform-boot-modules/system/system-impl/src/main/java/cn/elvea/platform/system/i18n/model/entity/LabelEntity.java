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
@Table(name = "sys_label")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class LabelEntity extends BaseEntity {
    /**
     * 分组
     */
    private String group;
    /**
     * 标识
     */
    private String code;
    /**
     * 简体中文
     */
    private String zhLabel;
    private Boolean zhLabelStaticInd;
    /**
     * 繁体中文
     */
    private String zhTwLabel;
    private Boolean zhTwLabelStaticInd;
    /**
     * 英语
     */
    private String enLabel;
    private Boolean enLabelStaticInd;
    /**
     * 法语
     */
    private String frLabel;
    private Boolean frLabelStaticInd;
    /**
     * 日语
     */
    private String jaLabel;
    private Boolean jaLabelStaticInd;
}
