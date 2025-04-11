package cc.elvea.platform.system.keyword.model.entity;

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
@Table(name = "sys_keyword")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class KeywordEntity extends BaseEntity {
    /**
     * 关键字内容
     */
    private String content;
}
