package cn.elvea.platform.system.security.model.entity;

import cn.elvea.platform.commons.core.data.mybatis.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
 * @since 0.0.1
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_authorization_consent")
public class AuthorizationConsentEntity extends BaseEntity {
    /**
     *
     */
    private String uuid;
    /**
     *
     */
    private String clientId;
    /**
     *
     */
    private String principalName;
    /**
     *
     */
    private String authorities;
}
