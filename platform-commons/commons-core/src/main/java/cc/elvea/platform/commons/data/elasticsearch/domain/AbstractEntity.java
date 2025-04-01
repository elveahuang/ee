package cc.elvea.platform.commons.data.elasticsearch.domain;

import cc.elvea.platform.commons.data.core.domain.IdEntity;
import org.springframework.data.annotation.Id;

/**
 * @author elvea
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
