package top.baihu.platform.commons.data.mybatis.id;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import top.baihu.platform.commons.core.sequence.SequenceManager;

/**
 * 自定义主键生成器
 *
 * @author elvea
 * @see IdentifierGenerator
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
