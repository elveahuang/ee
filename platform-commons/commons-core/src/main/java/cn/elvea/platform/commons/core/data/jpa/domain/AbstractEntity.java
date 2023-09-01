package cn.elvea.platform.commons.core.data.jpa.domain;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.jpa.id.CustomIdentifierGenerator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author elvea
 * @see IdEntity
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class AbstractEntity implements IdEntity {

    protected Long id;

    @Id
    @GeneratedValue(generator = "hibernateIdentifierGenerator")
    @GenericGenerator(name = "hibernateIdentifierGenerator", type = CustomIdentifierGenerator.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
