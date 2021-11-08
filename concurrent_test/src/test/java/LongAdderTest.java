import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {
    LongAdder test1 = new LongAdder();
    @Test
    public void longAdder() throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(
                5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            pool.execute(() -> {
                test1.add(1L);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(test1.longValue());
    }

    static AtomicLong test2 = new AtomicLong();
    @Test
    public void atomicLong() throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(
                5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            pool.execute(() -> {
                test2.getAndAdd(1L);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(test2.longValue());
    }

    static Long test3 = 0L;
    @Test
    public void normalAdd() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                test3++;
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(test3);
    }
}
