package top.baihu.platform.commons.data.jpa.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import top.baihu.platform.commons.data.core.domain.IdEntity;
import top.baihu.platform.commons.data.jpa.id.Sequence;

/**
 * @author elvea
 * @see IdEntity
 */
@MappedSuperclass
public abstract class AbstractEntity implements IdEntity {

    @Id
    @Sequence
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
