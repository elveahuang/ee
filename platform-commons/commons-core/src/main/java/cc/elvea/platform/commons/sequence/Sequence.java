package cc.elvea.platform.commons.sequence;

/**
 * @author elvea
 * @since 24.1.0
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
