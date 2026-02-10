package cc.wdev.platform.system.core.domain.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private String description;
    /**
     * 类型
     */
    private DictRelationVo type;
}
