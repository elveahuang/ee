package cc.elvea.platform.system.announcement.model.vo;

import cc.elvea.platform.system.dict.model.vo.DictRelationVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
