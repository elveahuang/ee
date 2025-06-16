package cc.elvea.platform.system.security.model.entity;

import cc.elvea.platform.commons.data.mybatis.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
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
