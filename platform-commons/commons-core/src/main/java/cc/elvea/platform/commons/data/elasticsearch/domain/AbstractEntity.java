package cc.elvea.platform.commons.data.elasticsearch.domain;

import cc.elvea.platform.commons.data.domain.IdEntity;
import org.springframework.data.annotation.Id;

/**
 * @author elvea
 * @since 24.1.0
 */
public abstract class AbstractEntity implements IdEntity {

    @Id
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
