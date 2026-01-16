package cc.wdev.platform.commons.data.elasticsearch.domain;

import cc.wdev.platform.commons.data.core.domain.IdEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * @author elvea
 */
public abstract class AbstractEntity implements IdEntity {

    @Id
    @Getter
    @Setter
    protected Long id;

}
