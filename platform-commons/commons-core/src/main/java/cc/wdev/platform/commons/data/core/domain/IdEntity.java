package cc.wdev.platform.commons.data.core.domain;

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
     * @return String
     */
    String getIdStr();

    /**
     * @param id Long
     */
    void setId(Long id);

    /**
     * @param id String
     */
    void setId(String id);

}
