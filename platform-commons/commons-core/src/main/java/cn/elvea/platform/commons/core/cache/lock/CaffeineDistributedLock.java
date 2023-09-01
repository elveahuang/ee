package cn.elvea.platform.commons.core.cache.lock;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author elvea
 * @since 0.0.1
 */
@Getter
@Slf4j
public class CaffeineDistributedLock implements Lock {

    private final String key;

    private final int leaseMilliseconds;

    public CaffeineDistributedLock(@NonNull String key, int leaseMilliseconds) {
        this.key = key;
        this.leaseMilliseconds = leaseMilliseconds;
    }

    @Override
    public void lock() {
        //
    }

    @Override
    public void lockInterruptibly() {
        //
    }

    @Override
    public boolean tryLock() {
        return true;
    }

    @Override
    public boolean tryLock(@NonNull long time, @NonNull TimeUnit unit) throws InterruptedException {
        return true;
    }

    @Override
    public void unlock() {
        //
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }

}
