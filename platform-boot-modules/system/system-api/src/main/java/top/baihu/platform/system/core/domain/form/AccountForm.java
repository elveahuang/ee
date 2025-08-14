package top.baihu.platform.system.core.domain.form;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.baihu.platform.commons.annotations.DateTimeFormat;
import top.baihu.platform.commons.annotations.JsonFormat;
import top.baihu.platform.commons.constants.DateTimeConstants;

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
    protected Long id;
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
