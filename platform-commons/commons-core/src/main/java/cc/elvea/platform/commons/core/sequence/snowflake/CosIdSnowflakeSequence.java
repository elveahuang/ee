package cc.elvea.platform.commons.core.sequence.snowflake;

import cc.elvea.platform.commons.core.sequence.Sequence;
import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.provider.IdGeneratorProvider;

/**
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 * <p>
 * 分布式高效有序ID生产黑科技(sequence)
 * <p>
 * 代码来自
 * <p>MyBatis-Plus：<a href="https://mybatis.plus">...</a></p>
 *
 * @author hubin
 * @since 2016-08-18
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
