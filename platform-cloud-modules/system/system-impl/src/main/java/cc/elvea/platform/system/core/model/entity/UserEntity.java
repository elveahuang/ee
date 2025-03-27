package cc.elvea.platform.system.core.model.entity;

import cc.elvea.platform.commons.data.core.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author elvea
 */
@Data
@TableName("sys_user")
public class UserEntity implements IdEntity {
    /**
     * 主键
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
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
    private String mobile;
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
    /**
     * 启用状态
     */
    private Boolean active;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;
    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastModifiedAt;
    /**
     * 最后修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lastModifiedBy;
    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;
    /**
     * 删除人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deletedBy;
}
