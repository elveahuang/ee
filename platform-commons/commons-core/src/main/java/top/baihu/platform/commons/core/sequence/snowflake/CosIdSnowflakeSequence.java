package top.baihu.platform.commons.core.sequence.snowflake;

import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.provider.IdGeneratorProvider;
import top.baihu.platform.commons.core.sequence.Sequence;

/**
 * @author elvea
 */
@Slf4j
public class CosIdSnowflakeSequence implements Sequence {

    private final IdGeneratorProvider provider;

    public CosIdSnowflakeSequence(IdGeneratorProvider provider) {
        this.provider = provider;
    }

    @Override
    public long nextId() {
        return this.provider.getShare().generate();
    }

}
