package cc.elvea.platform.system.core.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String displayName;
    /**
     * 全名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机国家区号
     */
    private String mobileCountryCode;
    /**
     * 手机
     */
    private String mobileNumber;
    /**
     * 密码
     */
    private String password;
    /**
     * 证件类型
     */
    private String idCardType;
    /**
     * 证件号码
     */
    private String idCardNo;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private LocalDate birthday;
    /**
     * 备注
     */
    private String description;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 密码过期时间
     */
    private String passwordExpireAt;
    /**
     * 最后一次输入错误密码的时间
     */
    private String passwordErrorAt;
    /**
     * 输入错误密码的次数
     */
    private Integer passwordErrorCount;
    /**
     * 最后登录状态
     */
    private String lastLoginStatus;
    /**
     * 最后登录时间
     */
    private String lastLoginAt;
}
