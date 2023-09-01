package cn.elvea.platform.commons.core.sequence;

import cn.elvea.platform.commons.core.sequence.snowflake.DefaultSnowflakeSequence;

/**
 * @author elvea
 * @since 0.0.1
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
