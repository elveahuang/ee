package cc.elvea.platform.commons.data.jdbc.domain;

import cc.elvea.platform.commons.data.domain.IdEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Id;

/**
 * @author elvea
 * @see IdEntity
 * @since 24.1.0
 */
public abstract class AbstractEntity implements IdEntity {

    @Id
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
