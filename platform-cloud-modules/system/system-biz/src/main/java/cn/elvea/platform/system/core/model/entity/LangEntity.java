package cn.elvea.platform.system.core.model.entity;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LangTypeEntity
 *
 * @author elvea
 * @since 0.0.1
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
