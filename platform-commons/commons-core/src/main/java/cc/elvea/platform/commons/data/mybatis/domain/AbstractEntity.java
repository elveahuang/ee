package cc.elvea.platform.commons.data.mybatis.domain;

import cc.elvea.platform.commons.data.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author elvea
 * @see IdEntity
 * @since 24.1.0
 */
public abstract class AbstractEntity implements IdEntity {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
