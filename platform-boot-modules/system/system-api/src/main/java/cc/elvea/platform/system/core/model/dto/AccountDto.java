package cc.elvea.platform.system.core.model.dto;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户名
     */
    private String displayName;
    /**
     * 用户名
     */
    private String name;
    /**
     * 手机国家区号
     */
    private String mobileCountryCode;
    /**
     * 手机
     */
    private String mobileNumber;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private LocalDate birthday;
    /**
     * 描述
     */
    private String description;
    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime lastModifiedAt;
    /**
     * 用户所属角色
     */
    private List<String> roles;
    /**
     * 用户所拥有的权限
     */
    private List<String> authorities;
}
