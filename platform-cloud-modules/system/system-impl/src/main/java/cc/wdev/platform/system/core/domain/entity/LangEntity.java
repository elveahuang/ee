package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.core.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

/**
 * LangTypeEntity
 *
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_lang")
public class LangEntity implements IdEntity {
    /**
     * 主键
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 国家地区
     */
    private String locale;
    /**
     * 多语言文本
     */
    private String label;
    /**
     * 备注说明
     */
    private String description;
    /**
     * 是否默认语言
     */
    private Boolean defaultInd;
    /**
     * 启用状态
     */
    private Boolean active;
}
