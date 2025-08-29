package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 顶层主体表
 * 用于用户体系和账号体系的上层抽象，方便后续大平台化下的账号统一
 * 用户体系用于后台系统
 * 账号体系用于前台系统
 *
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_identity")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class IdentityEntity extends BaseEntity {
    private String uuid;
}
