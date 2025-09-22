package cc.wdev.dev.webapp.mybatis.domain.entity;

import cc.wdev.platform.commons.data.mybatis.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sp_user")
public class MpUserEntity extends BaseEntity {
    /**
     * 租户
     */
    private Long tenantId;
    /**
     * 用户名
     */
    private String username;
}
