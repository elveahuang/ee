package top.baihu.platform.commons.data.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import top.baihu.platform.commons.data.core.domain.IdEntity;

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
