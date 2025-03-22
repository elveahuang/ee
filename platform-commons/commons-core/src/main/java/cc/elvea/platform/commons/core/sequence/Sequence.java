package cc.elvea.platform.commons.core.sequence;

/**
 * @author elvea
 */
public interface Sequence {

    long nextId();

    default String nextIdAsString() {
        return String.valueOf(this.nextId());
    }

    default String generateCode() {
        return Long.toString(this.nextId(), 36).toUpperCase();
    }

}
