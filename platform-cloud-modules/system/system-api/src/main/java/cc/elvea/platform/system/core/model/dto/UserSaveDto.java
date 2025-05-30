package cc.elvea.platform.system.core.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * UserSession
 *
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserSaveDto implements Serializable {
    /**
     * 用户名
     */
    private String username;
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
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 全名
     */
    private String name;
    /**
     * 昵称
     */
    private String displayName;
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
    private String birthday;
    /**
     * 备注
     */
    private String description;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 用户所属岗位
     */
    private List<Long> positionIdList;
    /**
     * 用户所属部门
     */
    private List<Long> organizationIdList;
}
