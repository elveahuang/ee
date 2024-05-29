package cc.elvea.platform.commons.sequence;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface Sequence {

    long nextId();

    String nextIdAsString();

}
