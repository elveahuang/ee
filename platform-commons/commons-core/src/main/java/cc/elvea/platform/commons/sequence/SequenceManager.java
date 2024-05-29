package cc.elvea.platform.commons.sequence;

import cc.elvea.platform.commons.sequence.snowflake.DefaultSnowflakeSequence;

/**
 * @author elvea
 * @since 24.1.0
 */
public class SequenceManager {

    private static volatile Sequence globalSequence = new DefaultSnowflakeSequence();

    public static Sequence getSequence() {
        return globalSequence;
    }

    public static void setSequence(Sequence sequence) {
        globalSequence = sequence;
    }

}
