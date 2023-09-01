package cn.elvea.platform.commons.core.data.elasticsearch.domain;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import org.springframework.data.annotation.Id;

/**
 * @author elvea
 * @since 0.0.1
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
