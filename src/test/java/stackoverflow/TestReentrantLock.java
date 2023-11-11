package stackoverflow;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class TestReentrantLock {

private final ReentrantLock lock = new ReentrantLock();

public void m() throws Exception {
    lock.lock();
    try (Closeable _c = lock::unlock){
        // 共有リソースへのアクセスなど
    }
}

@Test
public void test() throws Exception {
    m();
}

}
