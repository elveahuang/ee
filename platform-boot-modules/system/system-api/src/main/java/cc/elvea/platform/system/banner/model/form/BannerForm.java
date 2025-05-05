package cc.elvea.platform.system.banner.model.form;

import cc.elvea.platform.system.dict.model.vo.DictRelationVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BannerForm implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 标题
     */
    @NotEmpty
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 序号
     */
    private Integer idx;
    /**
     * 来源
     */
    private Integer source;
    /**
     * 类型
     */
    private DictRelationVo type;
    /**
     * 封面
     */
    private String cover;
}
