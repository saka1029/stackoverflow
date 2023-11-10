package stackoverflow;

import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class TestReentrantLock {

    private final ReentrantLock lock = new ReentrantLock();

    @Test
    public void test() {
        lock.lock();
        try {
            // 共有リソースへのアクセスなど
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void test2() {
        lock.lock();
        try (Closeable c = () -> lock.unlock()){
            // 共有リソースへのアクセスなど
        }
    }
}
