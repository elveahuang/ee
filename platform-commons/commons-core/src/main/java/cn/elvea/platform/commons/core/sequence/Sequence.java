package cn.elvea.platform.commons.core.sequence;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface Sequence {

    long nextId();

    String nextIdAsString();

}
