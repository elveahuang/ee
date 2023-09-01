package cn.elvea.platform.commons.core.data.mybatis.id;

import cn.elvea.platform.commons.core.sequence.SequenceManager;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * 自定义主键生成器
 *
 * @author elvea
 * @see IdentifierGenerator
 * @since 0.0.1
 */
public class CustomIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return SequenceManager.getSequence().nextId();
    }

    @Override
    public String nextUUID(Object entity) {
        return IdentifierGenerator.super.nextUUID(entity);
    }

}
