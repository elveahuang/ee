package cc.elvea.platform.commons.data.core.domain;

import java.io.Serializable;

/**
 * @author elvea
 */
public interface IdEntity extends Serializable {

    /**
     * @return Long
     */
    Long getId();

    /**
     * @param id Long
     */
    void setId(Long id);

}
