package cc.elvea.platform.system.log.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.SimpleEntity;
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
@Table(name = "sys_captcha_log")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class CaptchaLogEntity extends SimpleEntity {
    /**
     * 验证码类型
     */
    private String captchaType;
    /**
     * 验证码标识
     */
    private String captchaKey;
    /**
     * 验证码
     */
    private String captchaValue;
    /**
     * 接收人
     */
    private String email;
    private String mobileCountryCode;
    private String mobileNumber;
}
