package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.core.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;

/**
 * LangLabelEntity
 *
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_label")
public class LabelEntity implements IdEntity {
    /**
     * 主键
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 多语言标识
     */
    private String code;
    /**
     * 备注说明
     */
    private String description;
    /**
     * 简体中文
     */
    private String zhCnLabel;
    private Boolean zhCnSourceInd;
    private Boolean zhCnFinalInd;
    /**
     * 繁体中文
     */
    private String zhHkLabel;
    private Boolean zhHkSourceInd;
    private Boolean zhHkFinalInd;
    /**
     * 美式英语
     */
    private String enUsLabel;
    private Boolean enUsSourceInd;
    private Boolean enUsFinalInd;
    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @CreatedBy
    private Long createdBy;
    /**
     * 修改时间
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;
    /**
     * 修改人
     */
    @LastModifiedBy
    private Long updatedBy;
}
