package cc.elvea.platform.commons.data.jpa.domain;

import cc.elvea.platform.commons.data.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.id.CustomIdentifierGenerator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author elvea
 * @see IdEntity
 * @since 24.1.0
 */
@MappedSuperclass
public abstract class AbstractEntity implements IdEntity {

    @Id
    @GeneratedValue(generator = "hibernateIdentifierGenerator")
    @GenericGenerator(name = "hibernateIdentifierGenerator", type = CustomIdentifierGenerator.class)
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
