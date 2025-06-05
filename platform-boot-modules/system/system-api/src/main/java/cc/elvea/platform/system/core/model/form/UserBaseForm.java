package cc.elvea.platform.system.core.model.form;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseForm implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 昵称
     */
    private String displayName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDate birthday;
}
