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
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_dictionary_relation")
public class DictionaryRelationEntity implements IdEntity {
    /**
     * 主键
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     *
     */
    private Long tagTypeId;
    /**
     *
     */
    private Long tagId;
    /**
     *
     */
    private Long targetType;
    /**
     *
     */
    private Long targetId;
    /**
     *
     */
    @CreatedDate
    private LocalDateTime createdAt;
    /**
     *
     */
    @CreatedBy
    private Long createdBy;
}
