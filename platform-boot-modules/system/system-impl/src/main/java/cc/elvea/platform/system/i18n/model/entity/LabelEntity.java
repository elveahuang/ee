package cc.elvea.platform.system.i18n.model.entity;

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
    private String zhCnLabel;
    private Boolean zhCnStaticInd;
    /**
     * 繁体中文
     */
    private String zhTwLabel;
    private Boolean zhTwStaticInd;
    /**
     * 英语
     */
    private String enLabel;
    private Boolean enStaticInd;
    /**
     * 日语
     */
    private String jaLabel;
    private Boolean jaStaticInd;
    /**
     * 韩语
     */
    private String krLabel;
    private Boolean krStaticInd;
    /**
     * 法语
     */
    private String frLabel;
    private Boolean frStaticInd;
}
