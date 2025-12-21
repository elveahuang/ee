package cc.wdev.platform.system.core.domain.form;


import cc.wdev.platform.commons.annotations.DateTimeFormat;
import cc.wdev.platform.commons.annotations.JsonFormat;
import cc.wdev.platform.commons.constants.DateTimeConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String displayName;
    private String sex;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDate birthday;
    private String description;
}
